package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	Bookings bookings;

	@Mock
	Booking booking;

	@Mock
	Boardrooms boardrooms;

	@Mock
	BookingsStrategy bookingsStrategy;

	@Mock
	BoardroomsStrategy boardroomsStrategy;
	
	@Mock
	BookingAssignedTrigger trigger;
	
	@Mock
	BookingAssignedTrigger secondTrigger;

	@Before
	public void setUp() {
		setUpBookingStrategyMock();
		bookings = new Bookings();
	}

	@Test
	public void isEmptyShouldReturnTrueIfEmpty() {
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void isEmptyShouldReturnFalseIfNotEmpty() {
		bookings.add(booking);
		assertFalse(bookings.isEmpty());
	}

	@Test
	public void addBookingShouldAddTheBooking() {
		bookings.add(booking);
		assertFalse(bookings.isEmpty());
	}
	
	@Test
	public void addOneBookingShouldHaveSizeOfOne() {
		bookings.add(booking);
		assertEquals(1, bookings.getSize());
	}
	
	@Test
	public void addTwoBookingsShouldHaveSizeOfTwo() {
		bookings.add(booking);
		bookings.add(booking);
		assertEquals(2, bookings.getSize());
	}

	@Test
	public void removeBookingShouldRemoveTheBooking() {
		bookings.add(booking);
		bookings.remove(booking);
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void assignShouldRemoveTheBookingIfItIsAssigned() {
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
		assertTrue(bookings.isEmpty());
	}
	
	private void setUpBookingStrategyMock(){
		List<Booking> formatedList = new ArrayList<Booking>(Arrays.asList(booking));
		when(bookingsStrategy.sort(any())).thenReturn(formatedList);
	}
}
