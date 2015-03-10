package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.bookingExceptions.InvalidPriorityException;

public class Booking implements BookingAssignable {
	
	private final int DEFAULT_PRIORITY = 3;
	private int numberOfSeatsNeeded;
	private boolean assigned;
	private int priority;

	public Booking(int numberOfSeatsNeeded) {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		priority = DEFAULT_PRIORITY;
	}
	
	public void setPriority(int priority) {
		validatePriorityValue(priority);
		this.priority = priority;
	}

	public void assign() {
		assigned = true;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	private void validatePriorityValue(int priorityValueToValidate) {
		if (priorityValueToValidate < 1 || priorityValueToValidate > 5) {
			throw new InvalidPriorityException();
		}
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return Integer.compare(this.priority, bookingToCompare.priority);
	}
}
