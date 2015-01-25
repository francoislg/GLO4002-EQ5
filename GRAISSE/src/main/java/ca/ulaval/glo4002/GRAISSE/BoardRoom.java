package ca.ulaval.glo4002.GRAISSE;

public class Boardroom {
	private int numberOfSeats;
	private String name;
	private boolean available;

	public Boardroom(String name, int numberOfSeats) {
		this.name = name;
		this.numberOfSeats = numberOfSeats;
		this.available = true;
	}

	private boolean isAvailable() {
		return this.available;
	}

	public boolean isMyName(String name) {
		return this.name == name;
	}

	public boolean verifyNumberOfSeats(Booking bookingToVerify) {
		return bookingToVerify.verifyNumberOfSeats(numberOfSeats);
	}

	public boolean assign(Booking bookingToAssign) {
		if (!verifyNumberOfSeats(bookingToAssign)) {
			return false;
		}
		if (isAvailable()) {
			bookingToAssign.assign();
			available = false;
			return true;
		}
		return false;
	}
}
