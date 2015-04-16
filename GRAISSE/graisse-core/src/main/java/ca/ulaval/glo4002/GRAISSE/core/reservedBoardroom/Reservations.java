package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.ReservationAssigner;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingCanceller;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Reservations implements ReservationAssigner, BookingCanceller {
	private ReservationsRepository reservationsRepository;

	public Reservations(ReservationsRepository reservationsRepository) {
		this.reservationsRepository = reservationsRepository;
	}

	@Override
	public void cancelBooking(AssignedBooking assignedBooking) {
		Reservation reservationToCancel = reservationsRepository.retrieve(assignedBooking);
		reservationToCancel.cancel();
		reservationsRepository.remove(reservationToCancel);
	}

	@Override
	public boolean isAvailable(Boardroom boardroom) {
		return reservationsRepository.existsWithBoardroom(boardroom);
	}

	@Override
	public void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign) {
		Reservation reservation = new Reservation(boardroomToAssign, bookingToAssign);
		reservationsRepository.persist(reservation);
	}

	@Override
	public BookingDTO retrieveReservation(Email email, String boardroomName) {
		Reservation reservation = reservationsRepository.retrieve(email, boardroomName);
		return convertToBookingDTO(reservation);
	}

	@Override
	public boolean hasReservation(Email email, String boardroomName) {
		return reservationsRepository.hasReservation(email, boardroomName);
	}
	
	private BookingDTO convertToBookingDTO(Reservation reservation){
		return new BookingDTO(reservation.getID(), reservation.getNumberOfSeats(), reservation.getPromoterEmail(), reservation.getState(), reservation.getBoardroomName());
	}
}
