package ca.ulaval.glo4002.GRAISSE;

public class Booking {
	private int numberOfSeatsNeeded;
	private boolean assigned;
	private int priority;

	public Booking(int numberOfSeatsNeeded) {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
	}

	public Booking(int numberOfSeatsNeeded, int priorityValue) throws invalidPriorityValue {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		validatePriorityValue(priorityValue);
	}

	public void assign() {
		assigned = true;
	}

	public boolean isAssign() {
		return assigned;
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}

	private void validatePriorityValue(int PriorityValueToValidate) throws invalidPriorityValue {
		if (PriorityValueToValidate < 1 || PriorityValueToValidate > 5) {
			throw new invalidPriorityValue();
		}
	}
}
