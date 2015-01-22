package ca.ulaval.glo4002.GRAISSE;

public class BoardRoom {
	int numberOfSeats;

	public BoardRoom(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public boolean verifyNumberOfSeats(int numberOfSeatsNeeded) {
		return numberOfSeatsNeeded <= this.numberOfSeats;
	}

}
