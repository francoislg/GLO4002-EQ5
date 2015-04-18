package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Reservation {

	private AssignedBoardroom assignedBoardroom;
	private BookingAssignable assignedBooking;

	public Reservation(AssignedBoardroom assignedBoardroom, BookingAssignable bookingToAssign) {
		this.assignedBoardroom = assignedBoardroom;
		this.assignedBooking = bookingToAssign;
	}

	public boolean containsBooking(AssignedBooking assignedBooking) {
		return assignedBooking.equals(assignedBooking);
	}

	public boolean containsBoardroom(AssignedBoardroom assignedBoardroom) {
		return this.assignedBoardroom.equals(assignedBoardroom);
	}

	public boolean hasPromoter(Email promoter) {
		return assignedBooking.hasPromoter(promoter);
	}

	public boolean hasBoardroomName(String boardroomName) {
		return assignedBoardroom.hasName(boardroomName);
	}

	public void cancel() {
		assignedBooking.cancel();
	}

	public int getNumberOfSeats() {
		return assignedBooking.getNumberOfSeats();
	}

	public String getPromoterEmail() {
		return assignedBooking.getPromoterEmail();
	}

	public BookingState getState() {
		return assignedBooking.getState();
	}

	public String getBoardroomName() {
		return assignedBoardroom.getName();
	}

	public BookingID getBookingID() {
		return assignedBooking.getID();
	}

	public boolean hasBookingID(BookingID bookingID) {
		return assignedBooking.hasID(bookingID);
	}
}
