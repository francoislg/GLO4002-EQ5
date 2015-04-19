package ca.ulaval.glo4002.GRAISSE.core.reservation;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.AssignedBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Reservation {

	private AssignedBoardroom assignedBoardroom;
	private AssignedBooking assignedBooking;
	private Boolean isCancel;

	public Reservation(AssignedBoardroom assignedBoardroom, AssignedBooking bookingToAssign) {
		this.assignedBoardroom = assignedBoardroom;
		this.assignedBooking = bookingToAssign;
		this.isCancel = false;
	}

	public boolean containsBooking(AssignedBooking booking) {
		return assignedBooking.equals(booking);
	}

	public boolean containsBoardroom(AssignedBoardroom boardroom) {
		return assignedBoardroom.equals(boardroom);
	}

	public boolean hasPromoter(Email promoter) {
		return assignedBooking.hasPromoter(promoter);
	}

	public boolean hasBoardroomName(String boardroomName) {
		return assignedBoardroom.hasName(boardroomName);
	}

	public void cancel() {
		isCancel = true;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public int getNumberOfSeats() {
		return assignedBooking.getNumberOfSeats();
	}

	public Email getPromoterEmail() {
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
