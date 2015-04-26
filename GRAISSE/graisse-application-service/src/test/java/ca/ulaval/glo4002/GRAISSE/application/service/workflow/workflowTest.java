package ca.ulaval.glo4002.GRAISSE.application.service.workflow;

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
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class workflowTest {
	
	User user;
	User responsible;
	Bookings bookings;
	Boardrooms boardrooms;
	Reservations reservations;
	Booker booker;
	Canceler canceler;
	
	@Before
	public void setUp() {
		user = new User(new Email("random@email.ca"));
		responsible = new User(new Email("responsible@email.ca"));
		
		BookingRepository bookingRepository = ServiceLocator.getInstance().resolve(BookingRepository.class);
		BoardroomRepository boardroomRepository = ServiceLocator.getInstance().resolve(BoardroomRepository.class);
		ReservationRepository reservationRepository = ServiceLocator.getInstance().resolve(ReservationRepository.class);
		
		bookings = new Bookings(bookingRepository);
		boardrooms = new Boardrooms(boardroomRepository);
		reservations = new Reservations(reservationRepository, boardrooms, bookings);
		booker = new Booker(bookings, boardrooms, reservations);
		canceler = new Canceler(bookingRepository, reservationRepository);
	}
	
	public void givenANewReservationShouldBeInRepository() {

	}
	
	
	public void givenANewReservationStatusShouldNotbeCancelled() {

	}
	
	public void givenAReservationInRepositoryCancelShouldCancelTheReservation() {

	}
	
}


