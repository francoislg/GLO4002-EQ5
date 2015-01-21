package org.graisseLibCode;

public class Boardroom {
	int numberOfSeats;

	public Boardroom(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public boolean verifyNumberOfSeats(int numberOfSeatsNeeded) {
		return numberOfSeatsNeeded <= this.numberOfSeats;
	}

}
