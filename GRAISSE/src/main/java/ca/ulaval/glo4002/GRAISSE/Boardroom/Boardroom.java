package ca.ulaval.glo4002.GRAISSE.boardroom;

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
		return available;
	}

	public boolean hasName(String name) {
		return this.name.equals(name);
	}

	public boolean verifyNumberOfSeats(BookingAssignable bookingToVerify) {
		return bookingToVerify.verifyNumberOfSeats(numberOfSeats);
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public boolean assign(BookingAssignable bookingToAssign) {
		if (isAvailable() && verifyNumberOfSeats(bookingToAssign)) {
			bookingToAssign.assign();
			available = false;
			return true;
		}
		return false;
	}

	public int compareNumberOfSeatsToBoardroomNumberOfSeats(Boardroom boardrooomToCompare) {
		return Integer.compare(numberOfSeats, boardrooomToCompare.numberOfSeats);
	}
}
