package ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest;

import ca.ulaval.glo4002.GRAISSE.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;


public class CompletedBookingRequest {

	private AssignedBoardroom assignedBoardroom;
	private AssignedBooking assignedBooking;

	public CompletedBookingRequest(AssignedBoardroom assignedBoardroom, AssignedBooking assignedBooking) {
		this.assignedBoardroom = assignedBoardroom;
		this.assignedBooking = assignedBooking;
	}

	public boolean containsBooking(AssignedBooking assignedBooking) {
		return this.assignedBooking.equals(assignedBooking);
	}

}
