package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;

@RunWith(MockitoJUnitRunner.class)
public class BookingInMemoryRepositoryTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

	@Mock
	Booking booking1;

	@Mock
	Booking booking2;
	
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
		bookingInMemoryRepository.persist(booking1);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertTrue(result.contains(booking1));
	}

	@Test
	public void whenPersistingOneBookingRepositoryShouldContainOnlyOneBooking() {
		bookingInMemoryRepository.persist(booking1);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
	}

	@Test
	public void whenPersistingTwiceTheSameBookingRepositoryShouldContainOneBooking() {
		bookingInMemoryRepository.persist(booking1);
		bookingInMemoryRepository.persist(booking1);

		Collection<Booking> result = bookingInMemoryRepository.retrieveAll();
		assertEquals(1, result.size());
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

	@Test
	public void givenAListOfBookingsWhenSortingByDefaultShouldReturnSameList() {
		setUpOrderedBookingsRepo();
		assertEquals(orderedBookingList(), bookingInMemoryRepository.retrieveAll());
	}


	@Test
	public void givenAListOfBookingWhenGettingAllAssignedShouldOnlyReturnAssignedBookings() {
		setUp();
		
		when(booking1.isAssignable()).thenReturn(true);
		when(booking2.isAssignable()).thenReturn(true);
		
		bookingInMemoryRepository.persist(booking1);
		bookingInMemoryRepository.persist(booking2);

		Collection<Booking> assignableBookings = bookingInMemoryRepository.getAssignableBookings();
		
		assertTrue(assignableBookings.equals(Arrays.asList(booking1,booking2)));
	}
	
	@Test
	public void givenAListOfBookingNotAssignedGettingAssignedBookingsShouldReturnAnEmptyList() {
		setUp();
		
		when(booking1.isAssignable()).thenReturn(false);
		when(booking2.isAssignable()).thenReturn(false);
		
		bookingInMemoryRepository.persist(booking1);
		bookingInMemoryRepository.persist(booking2);

		Collection<Booking> assignableBookings = bookingInMemoryRepository.getAssignableBookings();
		
		assertTrue(assignableBookings.isEmpty());
	}
	
	
	@Ignore
	@Test
	public void givenAnUnorderedBookingsListWhenSortingByPriorityShouldReturnSorted() {
		setUp();
		setUpMocksForMultipleBookings();
		setUpUnorderedBookingsRepo();

		Collection<Booking> listOfBookings = bookingInMemoryRepository.retrieveSortedByPriority(); 

		assertTrue(listOfBookings.equals(orderedBookingList() ));
	}


	@Ignore
	@Test
	public void givenAListOfBookingWhenGettingAllBookingsForEmailShouldOnlyReturnBookingsWithThisEmail() {

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