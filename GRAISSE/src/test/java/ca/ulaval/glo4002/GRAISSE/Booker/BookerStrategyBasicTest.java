package ca.ulaval.glo4002.GRAISSE.Booker;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategy;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategyBasicTest {

	BookerStrategyBasic bookerStrategyBasic;

	@Mock
	BookingsStrategy bookingsStrategy;

	@Mock
	BoardroomsStrategy boardroomsStrategy;

	@Mock
	Bookings bookings;

	@Mock
	Boardrooms boardrooms;

	@Before
	public void setUp() {
		bookerStrategyBasic = new BookerStrategyBasic(bookingsStrategy, boardroomsStrategy);
	}

	@Test
	public void assignBookingsShouldCallAssignBookingsToBoardrooms() {
		bookerStrategyBasic.assignBookings(boardrooms, bookings);
		verify(bookings, times(1)).assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
	}
}
