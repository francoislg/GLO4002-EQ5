package ca.ulaval.glo4002.GRAISSE.Booking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingsStrategyPriorityTest {
	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	BookingsStrategyPriority bookingsStrategyPriority;

	@Mock
	Collection<Booking> bookingCollection;

	@Mock
	private Booking BookingWithHighPriority;

	@Mock
	private Booking BookingWithMediumPriority;

	@Mock
	private Booking BookingWithSmallPriority;

	@Before
	public void setUp() {
		bookingsStrategyPriority = new BookingsStrategyPriority();
		when(BookingWithSmallPriority.comparePriorityToBooking(any())).thenReturn(SMALLER);
		when(BookingWithMediumPriority.comparePriorityToBooking(BookingWithSmallPriority)).thenReturn(BIGGER);
		when(BookingWithMediumPriority.comparePriorityToBooking(BookingWithHighPriority)).thenReturn(SMALLER);
		when(BookingWithHighPriority.comparePriorityToBooking(any())).thenReturn(BIGGER);
	}

	@Test
	public void withBookingCollectionformatShouldReturnTheSameBoardroomCollection() {
		Collection<Booking> bookingList = new ArrayList<Booking>(Arrays.asList(BookingWithHighPriority, BookingWithMediumPriority, BookingWithSmallPriority));
		Collection<Booking> expectedBookingList = new ArrayList<Booking>(Arrays.asList(BookingWithSmallPriority, BookingWithMediumPriority,
				BookingWithHighPriority));
		Collection<Booking> result = bookingsStrategyPriority.format(bookingList);
		assertEquals(expectedBookingList, result);
	}

}
