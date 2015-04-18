package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.ReservationAssigner;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingCanceller;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Reservations implements ReservationAssigner, BookingCanceller {

	private ReservationRepository reservationRepository;

	public Reservations(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
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
}
