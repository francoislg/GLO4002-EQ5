package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class BookingInMemoryRepositoryTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

	@Mock
	Booking booking;

	@Mock
	Booking anotherBooking;

	@Mock
	Booking bookingWithHighPriority;

	@Mock
	Booking bookingWithMediumPriority;

	@Mock
	Booking secondBookingWithMediumPriority;

	@Mock
	Booking bookingWithLowPriority;

	@Mock
	Email promoter;

	@Mock
	BookingID bookingID;

	BookingInMemoryRepository bookingInMemoryRepository;

	@Before
	public void setUp() {
		bookingInMemoryRepository = new BookingInMemoryRepository();
	}

	@Test
	public void newRepositoryShouldBeEmpty() {
		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertTrue(result.isEmpty());
	}

	@Test
	public void whenPersistingOneBookingRepositoryShouldContainTheBooking() {
		bookingInMemoryRepository.persist(booking);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertTrue(result.contains(booking));
	}

	@Test
	public void whenPersistingOneBookingRepositoryShouldContainOnlyOneBooking() {
		bookingInMemoryRepository.persist(booking);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
	}

	@Test
	public void whenPersistingTwiceTheSameBookingRepositoryShouldContainOneBooking() {
		bookingInMemoryRepository.persist(booking);
		bookingInMemoryRepository.persist(booking);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
	}

	@Test
	public void givenAListOfBookingsWhenSortingByDefaultShouldReturnSameList() {
		setUpOrderedBookingsRepo();
		assertEquals(orderedBookingList(), bookingInMemoryRepository.retrieveAll());
	}

	@Test
	public void givenAnUnorderedBookingsListWhenSortingByPriorityShouldReturnSorted() {
		setUpMocksForMultipleBookings();
		setUpBookingsAllAssignable();
		setUpUnorderedBookingsRepo();
		assertEquals(orderedBookingList(), bookingInMemoryRepository.retrieveSortedByPriority());
	}

	@Test
	public void givenAssignablesBookingsWhenGettingThoseBookingsShouldOnlyReturnBookingsWhoAreAssignables() {
		setUpBookingsAssignableAndNotAssignable();
		setUpUnorderedBookingsRepo();
		assertEquals(bookingInMemoryRepository.getAssignableBookings().size(), 2);
	}

	@Test
	public void givenBookingsWhenGettingAllBookingsForEmailShouldOnlyReturnBookingsWithThisEmail() {
		setUpMocksWithEmailAndBookingId();
		assertEquals(bookingInMemoryRepository.retrieveAllForEmail(promoter), Arrays.asList(booking));
	}

	@Test
	public void givenBookingWhenRetrievingWithEmailAndIdShouldReturnTheBooking() {
		setUpMocksWithEmailAndBookingId();
		assertEquals(bookingInMemoryRepository.retrieve(promoter, bookingID), booking);
	}

	@Test(expected = BookingNotFoundException.class)
	public void givenBookingsWhenBookingDoesNotExistWithEmailAndIdShouldThrowBookingNotFoundException() {
		bookingInMemoryRepository.retrieve(promoter, bookingID);
	}

	@Test(expected = BookingNotFoundException.class)
	public void givenBookingWithDifferentIdWhenRetrievingShouldThrowBookingNotFoundException() {
		setUpMockBooking(true, false);
		bookingInMemoryRepository.retrieve(promoter, bookingID);
	}

	@Test(expected = BookingNotFoundException.class)
	public void givenBookingWithDifferentEmailWhenRetrievingShouldThrowBookingNotFoundException() {
		setUpMockBooking(false, true);
		bookingInMemoryRepository.retrieve(promoter, bookingID);
	}
	
	@Test
	public void givenBookingWithEmailAndIdWhenCheckingIfExistsShouldReturnTrue() {
		setUpMocksWithEmailAndBookingId();
		assertTrue(bookingInMemoryRepository.exists(promoter, bookingID));
	}
	
	@Test
	public void givenInexistentBookingWithEmailAndIdWhenCheckingIfExistsShouldReturnFalse() {
		setUpMockBooking(false, false);
		assertFalse(bookingInMemoryRepository.exists(promoter, bookingID));
	}
	
	@Test
	public void givenInexistentBookingWithEmailWhenCheckingIfExistsShouldReturnFalse() {
		setUpMockBooking(false, true);
		assertFalse(bookingInMemoryRepository.exists(promoter, bookingID));
	}
	
	@Test
	public void givenInexistentBookingWithIdWhenCheckingIfExistsShouldReturnFalse() {
		setUpMockBooking(true, false);
		assertFalse(bookingInMemoryRepository.exists(promoter, bookingID));
	}

	private void setUpMocksWithEmailAndBookingId() {
		when(booking.hasPromoter(promoter)).thenReturn(true);
		when(booking.hasID(bookingID)).thenReturn(true);

		when(anotherBooking.hasPromoter(promoter)).thenReturn(false);
		when(anotherBooking.hasID(bookingID)).thenReturn(false);

		bookingInMemoryRepository.persist(booking);
		bookingInMemoryRepository.persist(anotherBooking);
	}
	
	private void setUpMockBooking(boolean hasPromoter, boolean hasBookingID) {
		when(booking.hasPromoter(promoter)).thenReturn(hasPromoter);
		when(booking.hasID(bookingID)).thenReturn(hasBookingID);
		
		bookingInMemoryRepository.persist(booking);
	}

	private void setUpMocksForMultipleBookings() {
		when(bookingWithLowPriority.comparePriorityToBooking(any())).thenReturn(SMALLER);

		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithLowPriority)).thenReturn(BIGGER);
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(bookingWithMediumPriority.comparePriorityToBooking(secondBookingWithMediumPriority)).thenReturn(EQUAL);

		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithLowPriority)).thenReturn(BIGGER);
		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithMediumPriority)).thenReturn(EQUAL);

		when(bookingWithHighPriority.comparePriorityToBooking(any())).thenReturn(BIGGER);
	}

	private void setUpBookingsAllAssignable() {
		when(bookingWithLowPriority.isAssignable()).thenReturn(true);
		when(bookingWithMediumPriority.isAssignable()).thenReturn(true);
		when(secondBookingWithMediumPriority.isAssignable()).thenReturn(true);
		when(bookingWithHighPriority.isAssignable()).thenReturn(true);
	}

	private void setUpBookingsAssignableAndNotAssignable() {
		when(bookingWithLowPriority.isAssignable()).thenReturn(false);
		when(bookingWithMediumPriority.isAssignable()).thenReturn(false);
		when(secondBookingWithMediumPriority.isAssignable()).thenReturn(true);
		when(bookingWithHighPriority.isAssignable()).thenReturn(true);
	}

	private void setUpUnorderedBookingsRepo() {
		bookingInMemoryRepository.persist(bookingWithMediumPriority);
		bookingInMemoryRepository.persist(bookingWithHighPriority);
		bookingInMemoryRepository.persist(secondBookingWithMediumPriority);
		bookingInMemoryRepository.persist(bookingWithLowPriority);
	}

	private void setUpOrderedBookingsRepo() {
		bookingInMemoryRepository.persist(bookingWithLowPriority);
		bookingInMemoryRepository.persist(bookingWithMediumPriority);
		bookingInMemoryRepository.persist(secondBookingWithMediumPriority);
		bookingInMemoryRepository.persist(bookingWithHighPriority);
	}

	private Collection<Booking> orderedBookingList() {
		return Arrays.asList(bookingWithLowPriority, bookingWithMediumPriority, secondBookingWithMediumPriority, bookingWithHighPriority);
	}
}