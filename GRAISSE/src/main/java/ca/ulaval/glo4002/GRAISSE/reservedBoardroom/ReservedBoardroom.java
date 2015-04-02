package ca.ulaval.glo4002.GRAISSE.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public class ReservedBoardroom {

	private AssignedBoardroom assignedBoardroom;
	private BookingAssignable assignedBooking;

	public ReservedBoardroom(AssignedBoardroom assignedBoardroom, BookingAssignable bookingToAssign) {
		this.assignedBoardroom = assignedBoardroom;
		this.assignedBooking = bookingToAssign;
	}

	public boolean containsBooking(AssignedBooking assignedBooking) {
		return this.assignedBooking.equals(assignedBooking);
	}

	public boolean containsBoardroom(AssignedBoardroom assignedBoardroom) {
		return this.assignedBoardroom.equals(assignedBoardroom);
	}

	public void cancel() {
		assignedBooking.cancel();
	}

}
