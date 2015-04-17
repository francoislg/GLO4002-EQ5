package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;

@Entity
public class Reservation {

	@EmbeddedId
	private AssignedBoardroom assignedBoardroom;
	
	@EmbeddedId
	private BookingAssignable assignedBooking;

	public Reservation(AssignedBoardroom assignedBoardroom, BookingAssignable bookingToAssign) {
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
