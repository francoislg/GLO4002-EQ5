package ca.ulaval.glo4002.GRAISSE.Booking;

import ca.ulaval.glo4002.GRAISSE.Boardroom.BookingAssignable;

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
	
	public void setPriority(int priority) throws InvalidPriorityException{
		validatePriorityValue(priority);
		this.priority = priority;
	}

	@Override
	public void assign() {
		assigned = true;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

	@Override
	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}

	private void validatePriorityValue(int priorityValueToValidate) throws InvalidPriorityException {
		if (priorityValueToValidate < 1 || priorityValueToValidate > 5) {
			throw new InvalidPriorityException();
		}
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return Integer.compare(this.priority, bookingToCompare.priority);
	}
}
