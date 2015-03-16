package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
	Booking secondBookingWithMediumPriority;

	@Mock
	Booking bookingWithLowPriority;
	
	BookingsSortingStrategyByPriority bookingsSortingStrategyByPriority;
	
	@Before
	public void setUp() {
		setUpMocksForMultipleBookings();
		
		bookingsSortingStrategyByPriority = new BookingsSortingStrategyByPriority();
	}

	@Test
	public void givenAnUnorderedBookingsListWhenSortingWithStrategyShouldReturnAnOrderedList() {
		Collection<Booking> unorderedBookingList = unorderedBookingsList();
		Collection<Booking> expectedBookingList = orderedBookingList();
		
		Collection<Booking> result = bookingsSortingStrategyByPriority.sort(unorderedBookingList);
		
		assertEquals(expectedBookingList, result);
	}
	
	private void setUpMocksForMultipleBookings(){
		when(bookingWithLowPriority.comparePriorityToBooking(any())).thenReturn(SMALLER);
		
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithLowPriority)).thenReturn(BIGGER);
		when(bookingWithMediumPriority.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(bookingWithMediumPriority.comparePriorityToBooking(secondBookingWithMediumPriority)).thenReturn(EQUAL);
		
		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithLowPriority)).thenReturn(BIGGER);
		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithHighPriority)).thenReturn(SMALLER);
		when(secondBookingWithMediumPriority.comparePriorityToBooking(bookingWithMediumPriority)).thenReturn(EQUAL);
		
		when(bookingWithHighPriority.comparePriorityToBooking(any())).thenReturn(BIGGER);
	}
	
	private Collection<Booking> unorderedBookingsList(){
		return Arrays.asList(bookingWithMediumPriority, bookingWithHighPriority, secondBookingWithMediumPriority, bookingWithLowPriority);
	}
	
	private Collection<Booking> orderedBookingList(){
		return Arrays.asList(bookingWithLowPriority, bookingWithMediumPriority, secondBookingWithMediumPriority, bookingWithHighPriority);
	}
}