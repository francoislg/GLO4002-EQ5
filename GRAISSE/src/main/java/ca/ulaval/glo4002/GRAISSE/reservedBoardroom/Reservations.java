package ca.ulaval.glo4002.GRAISSE.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.boardroom.InterfaceReservationBoardroom;
import ca.ulaval.glo4002.GRAISSE.booker.InterfaceReservationBooking;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public class Reservations implements InterfaceReservationBoardroom, InterfaceReservationBooking {
	private ReservationRepository completedBookingRequestRepository;

	public Reservations(ReservationRepository completedBookingRequestRepository) {
		this.completedBookingRequestRepository = completedBookingRequestRepository;
	}

	@Override
	public void cancelBooking(AssignedBooking assignedBooking) {
		if (completedBookingRequestRepository.existWithBooking(assignedBooking)) {
			Reservation completedBookingRequestToCancel = completedBookingRequestRepository.retrieve(assignedBooking);
			completedBookingRequestToCancel.cancel();
			completedBookingRequestRepository.remove(completedBookingRequestToCancel);
		}

	}

	@Override
	public boolean isAvailable(Boardroom boardroom) {
		return completedBookingRequestRepository.existsWithBoardroom(boardroom);
	}

	@Override
	public void assign(AssignedBoardroom boardroomToAssign, BookingAssignable bookingToAssign) {
		Reservation completedBookingRequest = new Reservation(boardroomToAssign, bookingToAssign);
		completedBookingRequestRepository.persist(completedBookingRequest);
	}
}
