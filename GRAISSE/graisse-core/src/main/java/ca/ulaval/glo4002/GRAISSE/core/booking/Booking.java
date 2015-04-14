package ca.ulaval.glo4002.GRAISSE.core.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.user.User;


public class Booking implements BookingAssignable, AssignedBooking {

	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private boolean canceled;
	private Priority priority;
	private User promoter;

	public Booking(User promoter, int numberOfSeatsNeeded) {
		this(promoter, numberOfSeatsNeeded, DEFAULT_PRIORITY);
	}

	public Booking(User promoter, int numberOfSeatsNeeded, Priority priority) {
		this.promoter = promoter;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = priority;
		assigned = false;
		canceled = false;
	}

	public void assign() {
		assigned = true;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void cancel() {
		canceled = true;
	}

	public boolean hasPromoter(User user) {
		return promoter.equals(user);
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}
}