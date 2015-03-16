package ca.ulaval.glo4002.GRAISSE.booker;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategyBasicTest {

	@Mock
	BookingsStrategy bookingsStrategy;

	@Mock
	BoardroomsStrategy boardroomsStrategy;

	@Mock
	Bookings bookings;

	@Mock
	Boardrooms boardrooms;
	
	BookerStrategyBasic bookerStrategyBasic;

	@Before
	public void setUp() {
		bookerStrategyBasic = new BookerStrategyBasic(bookingsStrategy, boardroomsStrategy);
	}

	@Test
	public void assignBookingsShouldCallAssignBookingsToBoardrooms() {
		bookerStrategyBasic.assignBookings(boardrooms, bookings);
		verify(bookings).assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
	}
}
