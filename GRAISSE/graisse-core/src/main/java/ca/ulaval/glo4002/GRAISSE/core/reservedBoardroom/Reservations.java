package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.ReservationAssigner;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingCanceller;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

public class Reservations implements ReservationAssigner, BookingCanceller {

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

	@Override
	public void cancelBooking(AssignedBooking assignedBooking) {
		if (reservationRepository.exists(assignedBooking)) {
			Reservation reservationToCancel = reservationRepository.retrieve(assignedBooking);
			reservationToCancel.cancel();
			reservationRepository.remove(reservationToCancel);
		}
	}

	@Override
	public boolean isAvailable(Boardroom boardroom) {
		return reservationRepository.exists(boardroom);
	}

	@Override
	public void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign) {
		Reservation reservation = new Reservation(boardroomToAssign, bookingToAssign);
		reservationRepository.persist(reservation);
	}

	public void assignBookingsToBoardrooms(BookingsSortingStrategy bookingsSortingStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {

		Collection<Booking> formatedBookingList = bookings.getBookingsWithStrategy(bookingsSortingStrategy);

		for (Booking booking : formatedBookingList) {
			assignBookingToBoardrooms(booking, boardroomsSortingStrategy);

		}
	}

	public void assignBookingToBoardrooms(Booking booking, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardrooms.getBoardroomWithStrategy(boardroomsSortingStrategy);
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.canAssign(booking, this)) {
				assign(boardroom, booking);
				bookings.assignBooking(booking);
				notifyTriggers(booking);
				return;
			}
		}
		notifyTriggers(booking);
		throw new UnableToAssignBookingException();
	}

	@Override
	public BookingDTO retrieveReservation(Email email, BookingID bookingID) {
		Reservation reservation = reservationRepository.retrieve(email, bookingID);
		return convertToBookingDTO(reservation);
	}

	@Override
	public boolean hasReservation(Email email, BookingID bookingID) {
		return reservationRepository.exists(email, bookingID);
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
