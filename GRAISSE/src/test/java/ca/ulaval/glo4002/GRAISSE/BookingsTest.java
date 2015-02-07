package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	Bookings bookings;

	@Mock
	Booking booking;

	@Mock
	Boardrooms boardrooms;

	@Before
	public void setUp() {
		bookings = new Bookings();
	}

	@Test
	public void isEmptyShouldReturnTrueIfEmpty() {
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void isEmptyShouldReturnFalseIfNotEmpty() {
		bookings.addBooking(booking);
		assertFalse(bookings.isEmpty());
	}

	@Test
	public void addBookingShouldAddTheBooking() {
		bookings.addBooking(booking);
		assertFalse(bookings.isEmpty());
	}

	@Test
	public void removeBookingShouldRemoveTheBooking() {
		bookings.addBooking(booking);
		bookings.removeBooking(booking);
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void basicAssignShouldRemoveTheBookingIfItIsAssigned() {
		bookings.addBooking(booking);
		when(boardrooms.assignBookingToBoardroom(booking)).thenReturn(true);
		bookings.basicAssign(boardrooms);
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void maximiseAssignShouldRemoveTheBookingIfItIsAssigned() {
		bookings.addBooking(booking);
		when(boardrooms.assignToMaxSeatsBoardroom(booking)).thenReturn(true);
		bookings.maximiseAssign(boardrooms);
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void maximiseAssignShouldNotRemoveTheBookingIfItIsNotAssigned() {
		bookings.addBooking(booking);
		when(boardrooms.assignToMaxSeatsBoardroom(booking)).thenReturn(false);
		bookings.maximiseAssign(boardrooms);
		assertFalse(bookings.isEmpty());
	}

}
