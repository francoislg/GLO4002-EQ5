package ca.ulaval.glo4002.GRAISSE.Booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.Booking.Booking;
import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategy;

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
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/BookingsTest.java
	public void basicAssignShouldRemoveTheBookingIfItIsAssigned() {
		bookings.addBooking(booking);
		when(boardrooms.assignBookingToBoardroom(booking)).thenReturn(true);
		bookings.basicAssign(boardrooms);
		assertTrue(bookings.isEmpty());
	}
=======
	public void assignShouldRemoveTheBookingIfItIsAssigned() {
		List<Booking> formatedList = new ArrayList<Booking>(Arrays.asList(booking));
		when(boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy)).thenReturn(true);
		when(bookingsStrategy.format(any())).thenReturn(formatedList);
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Booking/BookingsTest.java

		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);

		assertTrue(bookings.isEmpty());
	}
}
