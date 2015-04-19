package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategyDefaultTest {

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	@Mock
	Reservations reservations;

	BookerStrategyDefault bookerStrategyBasic;

	@Before
	public void setUp() {
		bookerStrategyBasic = new BookerStrategyDefault(bookingsSortingStrategy, boardroomsSortingStrategy);
	}

	@Test
	public void assignBookingsShouldCallAssignBookingsToBoardrooms() {
		bookerStrategyBasic.assignBookings(reservations);
		verify(reservations).assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);
	}
}
