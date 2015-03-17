package ca.ulaval.glo4002.GRAISSE.booker;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategyDefaultTest {

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	@Mock
	Bookings bookings;

	@Mock
	Boardrooms boardrooms;
	
	BookerStrategyDefault bookerStrategyBasic;

	@Before
	public void setUp() {
		bookerStrategyBasic = new BookerStrategyDefault(bookingsSortingStrategy, boardroomsSortingStrategy);
	}

	@Test
	public void assignBookingsShouldCallAssignBookingsToBoardrooms() {
		bookerStrategyBasic.assignBookings(boardrooms, bookings);
		verify(bookings).assignBookingsToBoardrooms(boardrooms, bookingsSortingStrategy, boardroomsSortingStrategy);
	}
}
