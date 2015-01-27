package ca.ulaval.glo4002.GRAISSE;

public class Booking {
	private int numberOfSeatsNeeded;
	private boolean assigned;

	public Booking(int numberOfSeatsNeeded) {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
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
}
