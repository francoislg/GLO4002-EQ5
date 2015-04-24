package ca.ulaval.glo4002.GRAISSE.core.reservation;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

@RunWith(MockitoJUnitRunner.class)
public class ReservationsTest {

	@Mock
	ReservationRepository reservationRepository;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Bookings bookings;

	@Mock
	Reservation reservation;

	@Mock
	Boardroom boardroom;

	@Mock
	Booking booking;

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;
	@Mock
	Notifyer<Booking> observer;

	Reservations reservations;

	Collection<Reservation> reservationRepositoryWithReservations;
	Collection<Reservation> emptyReservationRepository;
	Collection<Booking> bookingsCollection;
	Collection<Boardroom> boardroomsCollection;

	@Before
	public void setUp() {
		reservations = new Reservations(reservationRepository, boardrooms, bookings);
		setUpBookingsCollection();
		setUpBoardroomsCollection();
	}

	@Test
	public void givenOneAvailableBoardroomAndOneAssignableBookingassignBookingsToBoardroomsShouldAssign() {

		when(boardroom.canAssign(booking)).thenReturn(true);
		when(reservationRepository.exists(boardroom)).thenReturn(false);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);

	}

	@Test(expected = UnableToAssignBookingException.class)
	public void givenNoAvailableBoardroomAndOneAssignableBookingassignBookingsToBoardroomsShouldThrowUnableToAssignBookingException() {

		when(boardroom.canAssign(booking)).thenReturn(true);
		when(reservationRepository.activeReservationWithBoardroomExist(boardroom)).thenReturn(true);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);
	}

	@Test(expected = UnableToAssignBookingException.class)
	public void givenAnAvailableBoardroomThatCantAssignTheBookingAndOneAssignableBookingassignBookingsToBoardroomsShouldThrowUnableToAssignBookingException() {

		when(boardroom.canAssign(booking)).thenReturn(false);
		when(reservationRepository.exists(boardroom)).thenReturn(false);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);
	}

	@Test
	public void givenNoAvailableBoardroomAndOneAssignableBookingassignBookingsToBoardroomsShouldNotifiyAssignation() {
		when(boardroom.canAssign(booking)).thenReturn(true);
		when(reservationRepository.exists(boardroom)).thenReturn(false);

		reservations.registerObserver(observer);
		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(observer).notify(booking);

	}

	private void setUpBookingsCollection() {
		Collection<Booking> bookingsCollection = new ArrayList<Booking>();
		bookingsCollection.add(booking);
		when(bookings.getBookingsWithStrategy(bookingsSortingStrategy)).thenReturn(bookingsCollection);

	}

	private void setUpBoardroomsCollection() {
		Collection<Boardroom> boardroomsCollection = new ArrayList<Boardroom>();
		boardroomsCollection.add(boardroom);
		when(boardrooms.getBoardroomsWithStrategy(boardroomsSortingStrategy)).thenReturn(boardroomsCollection);

	}
}
