package ca.ulaval.glo4002.GRAISSE;

public class Booking {
	private int numberOfSeatsNeeded;
	private boolean isAssign;

	public Booking(int numberOfSeatsNeeded) {
		isAssign = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
	}

	public void assign() {
		isAssign = true;
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}
}
