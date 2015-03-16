package ca.ulaval.glo4002.GRAISSE.booking;

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

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategyByPriority;

@RunWith(MockitoJUnitRunner.class)
public class BookingsSortingStrategyByPriorityTest {
	
	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;
	
	@Mock
	Booking bookingWithHighPriority;

	@Mock
	Booking bookingWithMediumPriority;
	
	@Mock
	Booking bookingWithMediumPriority2;

	@Mock
	Booking bookingWithSmallPriority;
	
	BookingsSortingStrategyByPriority bookingsSortingStrategyByPriority;
	
	@Before
	public void setUp() {
		bookingsSortingStrategyByPriority = new BookingsSortingStrategyByPriority();
		
		when(bookingWithSmallPriority.comparePriorityToBooking(any())).thenReturn(SMALLER);
		
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithSmallPriority)).thenReturn(BIGGER);
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithMediumPriority2)).thenReturn(EQUAL);
		
		when(bookingWithMediumPriority2.comparePriorityToBooking(bookingWithSmallPriority)).thenReturn(BIGGER);
		when(bookingWithMediumPriority2.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(bookingWithMediumPriority2.comparePriorityToBooking(bookingWithMediumPriority)).thenReturn(EQUAL);
		
		when(bookingWithHighPriority.comparePriorityToBooking(any())).thenReturn(BIGGER);
	}

	@Test
	public void withBookingCollectionSortShouldReturnTheSameBoardroomCollection() {
		Collection<Booking> bookingList = new ArrayList<Booking>(Arrays.asList(bookingWithMediumPriority,
				bookingWithHighPriority, bookingWithMediumPriority2, bookingWithSmallPriority));
		
		Collection<Booking> expectedBookingList = new ArrayList<Booking>(Arrays.asList(bookingWithSmallPriority, bookingWithMediumPriority,
				bookingWithMediumPriority2, bookingWithHighPriority));
		
		Collection<Booking> result = bookingsSortingStrategyByPriority.sort(bookingList);
		assertEquals(expectedBookingList, result);
	}
}