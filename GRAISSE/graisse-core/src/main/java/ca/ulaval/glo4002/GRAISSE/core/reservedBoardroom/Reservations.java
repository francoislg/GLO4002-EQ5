package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.InterfaceReservationBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.InterfaceReservationBooking;

public class Reservations implements InterfaceReservationBoardroom, InterfaceReservationBooking {
	private ReservationRepository completedBookingRequestRepository;

	public Reservations(ReservationRepository completedBookingRequestRepository) {
		this.completedBookingRequestRepository = completedBookingRequestRepository;
	}

	@Override
	public void cancelBooking(AssignedBooking assignedBooking) {
		Reservation completedBookingRequestToCancel = completedBookingRequestRepository.retrieve(assignedBooking);
		completedBookingRequestToCancel.cancel();
		completedBookingRequestRepository.remove(completedBookingRequestToCancel);
	}

	@Override
	public boolean isAvailable(Boardroom boardroom) {
		return !completedBookingRequestRepository.existsWithBoardroom(boardroom);
	}

	@Override
	public void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign) {
		Reservation completedBookingRequest = new Reservation(boardroomToAssign, bookingToAssign);
		completedBookingRequestRepository.persist(completedBookingRequest);
	}
}
