package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

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

	Reservations reservations;

	Collection<Reservation> reservationRepositoryWithReservations;
	Collection<Reservation> emptyReservationRepository;

	@Before
	public void setUp() {
		reservations = new Reservations(reservationRepository, boardrooms, bookings);
	}

	@Test
	public void givenOneAvailableBoardroomAndOneAssignableBookingassignBookingsToBoardroomsShouldAssignTheBooking() {
		Collection<Booking> bookingsCollection = new ArrayList<Booking>();
		bookingsCollection.add(booking);
		when(bookings.getBookingsWithStrategy(bookingsSortingStrategy)).thenReturn(bookingsCollection);
		Collection<Boardroom> boardroomsCollection = new ArrayList<Boardroom>();
		boardroomsCollection.add(boardroom);
		when(boardrooms.getBoardroomsWithStrategy(boardroomsSortingStrategy)).thenReturn(boardroomsCollection);
		when(boardroom.canAssign(booking)).thenReturn(true);
		when(reservationRepository.exists(boardroom)).thenReturn(false);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);
	}

	@Test(expected = UnableToAssignBookingException.class)
	public void givenNoAvailableBoardroomAndOneAssignableBookingassignBookingsToBoardroomsShouldThrowUnableToAssignBookingException() {
		Collection<Booking> bookingsCollection = new ArrayList<Booking>();
		bookingsCollection.add(booking);
		when(bookings.getBookingsWithStrategy(bookingsSortingStrategy)).thenReturn(bookingsCollection);
		Collection<Boardroom> boardroomsCollection = new ArrayList<Boardroom>();
		boardroomsCollection.add(boardroom);
		when(boardrooms.getBoardroomsWithStrategy(boardroomsSortingStrategy)).thenReturn(boardroomsCollection);
		when(boardroom.canAssign(booking)).thenReturn(true);
		when(reservationRepository.exists(boardroom)).thenReturn(true);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);
	}

	@Test(expected = UnableToAssignBookingException.class)
	public void givenAnAvailableBoardroomThatCantAssignTheBookingAndOneAssignableBookingassignBookingsToBoardroomsShouldThrowUnableToAssignBookingException() {
		Collection<Booking> bookingsCollection = new ArrayList<Booking>();
		bookingsCollection.add(booking);
		when(bookings.getBookingsWithStrategy(bookingsSortingStrategy)).thenReturn(bookingsCollection);
		Collection<Boardroom> boardroomsCollection = new ArrayList<Boardroom>();
		boardroomsCollection.add(boardroom);
		when(boardrooms.getBoardroomsWithStrategy(boardroomsSortingStrategy)).thenReturn(boardroomsCollection);
		when(boardroom.canAssign(booking)).thenReturn(false);
		when(reservationRepository.exists(boardroom)).thenReturn(false);

		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);

		verify(bookings).assignBooking(booking);
	}
}
