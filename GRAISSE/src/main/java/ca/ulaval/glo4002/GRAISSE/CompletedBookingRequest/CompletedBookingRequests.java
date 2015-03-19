package ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest;

import ca.ulaval.glo4002.GRAISSE.boardroom.InterfaceReservationBoardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.booking.InterfaceReservationBooking;

public class CompletedBookingRequests implements InterfaceReservationBoardroom, InterfaceReservationBooking {
	private CompletedBookingRequestRepository completedBookingRequestRepository;

	public CompletedBookingRequests(CompletedBookingRequestRepository completedBookingRequestRepository) {
		this.completedBookingRequestRepository = completedBookingRequestRepository;
	}

	@Override
	public void cancelBooking(AssignedBooking assignedBooking) {
		completedBookingRequestRepository.retrieve(assignedBooking);
	}

}
