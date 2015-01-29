package ca.ulaval.glo4002.GRAISSE;

public class Booking {
	private int numberOfSeatsNeeded;
	private boolean assigned;
	private int priority;

	public Booking(int numberOfSeatsNeeded, int priorityValue) throws InvalidPriorityValue {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		validatePriorityValue(priorityValue);
		this.priority = priorityValue;
	}

	public void assign() {
		assigned = true;
	}

	public int getPriorityValue() {
		return this.priority;
	}

	public boolean isAssign() {
		return assigned;
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}

	private void validatePriorityValue(int priorityValueToValidate) throws InvalidPriorityValue {
		if (priorityValueToValidate < 1 || priorityValueToValidate > 5) {
			throw new InvalidPriorityValue();
		}
	}

	public int compareNumberOfSeatsToBooking(Booking bookingToCompare) {
		return Integer.compare(this.numberOfSeatsNeeded, bookingToCompare.numberOfSeatsNeeded);
	}
}
