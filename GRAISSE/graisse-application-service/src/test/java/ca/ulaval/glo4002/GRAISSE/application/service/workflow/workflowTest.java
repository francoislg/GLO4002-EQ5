package ca.ulaval.glo4002.GRAISSE.application.service.workflow;

import static org.mockito.Mockito.verify;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategiesFactory;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategy;
import ca.ulaval.glo4002.GRAISSE.application.service.booking.BookerStrategiesFactory.StrategyType;
import ca.ulaval.glo4002.GRAISSE.application.service.canceling.Canceler;
import ca.ulaval.glo4002.GRAISSE.application.service.notification.BookingAssignationSendMailNotifier;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.Priority;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.junit.Ignore;


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
		booker = new Booker(bookings, boardrooms, reservations);
		canceler = new Canceler(bookingRepository, reservationRepository);
	}
	
	@Test
	public void givenANewReservationAddedInRepositoryShouldBeInRepository() {
		setUp();
		
		booker.addBooking(booking);
		verify(bookingRepository).persist(booking);
	}

	@Ignore
	@Test
	public void givenANewReservationStatusShouldNotbeCancelled() {
		setUp();
		
		booker.addBooking(booking);
		booker.assignBookings();
		
		assertTrue(booking.isAssigned());
	}
	

	@Test
	public void givenAReservationInRepositoryCancelShouldCancelTheReservation() {
		setUp();
		
		booker.addBooking(booking);
		booker.assignBookings();
		
		canceler.cancel(user.getEmail(), booking.getID());;
		assertFalse(booking.isAssigned());
	}
	
}


