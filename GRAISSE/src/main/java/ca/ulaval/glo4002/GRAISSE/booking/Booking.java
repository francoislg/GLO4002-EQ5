package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.user.User;

public class Booking implements BookingAssignable, AssignedBooking {

	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private boolean canceled;
	private Priority priority;
	private User creator;

	public Booking(User creator, int numberOfSeatsNeeded) {
		this(creator, numberOfSeatsNeeded, DEFAULT_PRIORITY);
	}

	public Booking(User creator, int numberOfSeatsNeeded, Priority priority) {
		this.creator = creator;
		this.assigned = false;
		this.canceled = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = priority;
	}

	public void assign() {
		assigned = true;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public boolean isAssignable() {
		return (!this.isAssigned() && !this.canceled);
	}

	public void cancel() {
		this.canceled = true;
	}

	public boolean hasCreator(User user) {
		return creator.equals(user);
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}
}