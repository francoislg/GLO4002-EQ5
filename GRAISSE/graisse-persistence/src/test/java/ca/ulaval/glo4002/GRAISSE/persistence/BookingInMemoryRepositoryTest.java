package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.BoardroomNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class BookingInMemoryRepositoryTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

	private static final String A_BOOKING_NAME = "Booking";
	private static final String A_INEXISTENT_BOOKING_NAME = "Inexistent Booking";
	
	@Mock
	Booking booking;

	@Mock
	Booking bookingThatIsNotInTheRepository;

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
	
	@Test(expected = BookingNotFoundException.class)
	public void givenAnInexistentBookingWhenRetrievingItShouldThrowBookingNotFoundException() {
		bookingInMemoryRepository.retrieve(promoter, A_INEXISTENT_BOOKING_NAME);
	}
	
	@Test
	public void givenAnExistentBookingWhenRetrievingShouldReturnIt() {
		setUpBookingWithPromoterAndName();
		bookingInMemoryRepository.persist(booking);
		
		assertEquals(booking, bookingInMemoryRepository.retrieve(promoter, A_BOOKING_NAME));
	}
	
	private void setUpBookingWithPromoterAndName() {
		when(booking.hasName(A_BOOKING_NAME)).thenReturn(true);
		when(booking.hasPromoter(promoter)).thenReturn(true);
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
		setUpUnorderedBookingsRepo();
		assertEquals(orderedBookingList(), bookingInMemoryRepository.retrieveSortedByPriority());
	}
	
	@Test
	public void whenGettingAssignableBookingsShouldReturnOnlyBookingsWithStateWaiting() {
		when(booking.isAssignable()).thenReturn(true);
		bookingInMemoryRepository.persist(booking);
		assertEquals(bookingInMemoryRepository.getAssignableBookings().size(), 1);
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
		
		when(bookingWithLowPriority.isAssignable()).thenReturn(true);
		when(bookingWithMediumPriority.isAssignable()).thenReturn(true);
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