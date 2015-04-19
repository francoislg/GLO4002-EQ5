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

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;

@RunWith(MockitoJUnitRunner.class)
public class BookingInMemoryRepositoryTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

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

	@Ignore
	@Test
	public void givenAnUnorderedBookingsListWhenSortingByPriorityShouldReturnSorted() {
		
	}

	@Ignore
	@Test
	public void givenAListOfBookingWhenGettingAllAssignedShouldOnlyReturnAssignedBookings() {

	}

	@Ignore
	@Test
	public void givenAListOfBookingWhenGettingAllBookingsForEmailShouldOnlyReturnBookingsWithThisEmail() {

	}

	private Collection<Booking> orderedBookingList() {
		return Arrays.asList(bookingWithLowPriority, bookingWithMediumPriority, secondBookingWithMediumPriority, bookingWithHighPriority);
	}
}