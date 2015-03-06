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
		bookings.add(booking);
		assertFalse(bookings.isEmpty());
	}

	@Test
	public void addBookingShouldAddTheBooking() {
		bookings.add(booking);
		assertFalse(bookings.isEmpty());
	}

	@Test
	public void removeBookingShouldRemoveTheBooking() {
		bookings.add(booking);
		bookings.remove(booking);
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void assignShouldRemoveTheBookingIfItIsAssigned() {
		List<Booking> formatedList = new ArrayList<Booking>(Arrays.asList(booking));
		when(boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy)).thenReturn(true);
		when(bookingsStrategy.sort(any())).thenReturn(formatedList);

		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);

		assertTrue(bookings.isEmpty());
	}

}
