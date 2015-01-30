package ca.ulaval.glo4002.GRAISSE;

public class Booking {
	private final int DEFAULT_PRIORITY = 3;
	private int numberOfSeatsNeeded;
	private boolean assigned;
	private int priority;

	public Booking(int numberOfSeatsNeeded) throws InvalidPriorityValue {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		priority = DEFAULT_PRIORITY;
	}
	
	public void setPriority(int priority) throws InvalidPriorityValue{
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
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}

	private void validatePriorityValue(int priorityValueToValidate) throws InvalidPriorityValue {
		if (priorityValueToValidate < 1 || priorityValueToValidate > 5) {
			throw new InvalidPriorityValue();
		}
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return Integer.compare(this.priority, bookingToCompare.priority);
	}
}
