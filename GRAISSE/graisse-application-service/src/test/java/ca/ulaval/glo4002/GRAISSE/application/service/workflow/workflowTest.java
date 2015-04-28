package ca.ulaval.glo4002.GRAISSE.application.service.workflow;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.canceling.Canceler;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

@RunWith(MockitoJUnitRunner.class)
public class workflowTest {

	private static final int A_NUMBER_OF_STEATS = 10;

	User user;
	User responsible;
	Bookings bookings;
	Boardrooms boardrooms;
	Reservations reservations;
	Booker booker;
	Canceler canceler;
	Booking booking;

	@Mock
	BookingRepository bookingRepository;

	@Mock
	BoardroomRepository boardroomRepository;

	@Mock
	ReservationRepository reservationRepository;

	@Before
	public void setUp() {
		user = new User(new Email("random@email.ca"));
		responsible = new User(new Email("responsible@email.ca"));
		booking = new Booking(user, A_NUMBER_OF_STEATS);

		bookings = new Bookings(bookingRepository);
		boardrooms = new Boardrooms(boardroomRepository);
		reservations = new Reservations(reservationRepository, boardrooms, bookings);
		booker = new Booker(bookings, reservations);
		canceler = new Canceler(bookingRepository, reservationRepository);
	}

	@Test
	public void givenAReservationInRepositoryCancelShouldCancelTheReservation() {
		booker.addBooking(booking);
		booker.assignBookings();

		canceler.cancel(booking);
		assertFalse(booking.isAssigned());
	}

}
