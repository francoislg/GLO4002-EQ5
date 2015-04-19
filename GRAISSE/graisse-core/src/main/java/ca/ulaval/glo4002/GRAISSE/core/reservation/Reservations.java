package ca.ulaval.glo4002.GRAISSE.core.reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

public class Reservations {

	private ReservationRepository reservationRepository;
	private Boardrooms boardrooms;
	private Bookings bookings;

	private List<Notifyer<BookingAssignable>> notifiers;

	public Reservations(ReservationRepository reservationRepository, Boardrooms boardrooms, Bookings bookings) {
		this.reservationRepository = reservationRepository;
		this.boardrooms = boardrooms;
		this.bookings = bookings;
		this.notifiers = new ArrayList<Notifyer<BookingAssignable>>();
	}

	private boolean isAvailable(Boardroom boardroom) {
		return !reservationRepository.exists(boardroom);
	}

	public void assign(Boardroom boardroom, Booking booking) {
		bookings.assignBooking(booking);
		Reservation reservation = new Reservation(boardroom, booking);
		reservationRepository.persist(reservation);
	}

	public void assignBookingsToBoardrooms(BookingsSortingStrategy bookingsSortingStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Booking> formatedBookingList = bookings.getBookingsWithStrategy(bookingsSortingStrategy);
		for (Booking booking : formatedBookingList) {
			assignBookingToBoardrooms(booking, boardroomsSortingStrategy);
		}
	}

	public void assignBookingToBoardrooms(Booking booking, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardrooms.getBoardroomsWithStrategy(boardroomsSortingStrategy);
		for (Boardroom boardroom : formatedBoardroomList) {
			if (isAvailable(boardroom) && boardroom.canAssign(booking)) {
				assign(boardroom, booking);
				notifyTriggers(booking);
				return;
			}
		}
		notifyTriggers(booking);
		throw new UnableToAssignBookingException();
	}

	public BookingDTO retrieveReservation(Email email, BookingID bookingID) {
		Reservation reservation = reservationRepository.retrieve(email, bookingID);
		return convertToBookingDTO(reservation);
	}

	private BookingDTO convertToBookingDTO(Reservation reservation) {
		return new BookingDTO(reservation.getBookingID(), reservation.getNumberOfSeats(), reservation.getPromoterEmail(), reservation.getState(),
				reservation.getBoardroomName());
	}

	private void notifyTriggers(BookingAssignable booking) {
		for (Notifyer<BookingAssignable> notifyer : notifiers) {
			notifyer.notify(booking);
		}
	}

	public void registerObserver(Notifyer<BookingAssignable> observer) {
		notifiers.add(observer);
	}
}
