package ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest;

import ca.ulaval.glo4002.GRAISSE.boardroom.AssignedBoardrooom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public class CompletedBookingRequest {

	private AssignedBoardrooom assignedBoardroom;
	private AssignedBooking assignedBooking;

	public CompletedBookingRequest(AssignedBoardrooom assignedBoardroom, AssignedBooking assignedBooking) {
		this.assignedBoardroom = assignedBoardroom;
		this.assignedBooking = assignedBooking;
	}

	public boolean containsBooking(AssignedBooking assignedBooking) {
		return this.assignedBooking.equals(assignedBooking);
	}

}
