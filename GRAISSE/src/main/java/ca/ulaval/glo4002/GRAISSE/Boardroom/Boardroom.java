package ca.ulaval.glo4002.GRAISSE.Boardroom;


public class Boardroom {
	private int numberOfSeats;
	private String nameOfBoardroom;
	private boolean available;

	public Boardroom(String name, int numberOfSeats) {
		this.nameOfBoardroom = name;
		this.numberOfSeats = numberOfSeats;
		this.available = true;
	}

	private boolean isAvailable() {
		return this.available;
	}

	public boolean isMyName(String name) {
		return this.nameOfBoardroom == name;
	}

	public boolean verifyNumberOfSeats(BookingAssignable bookingToVerify) {
		return bookingToVerify.verifyNumberOfSeats(numberOfSeats);
	}

	public int getNumberOfSeats() {
		return this.numberOfSeats;
	}

	public boolean assign(BookingAssignable bookingToAssign) {
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

	public int compareNumberOfSeatsToBoardroomNumberOfSeats(Boardroom boardrooomToCompare) {
		return Integer.compare(this.numberOfSeats, boardrooomToCompare.numberOfSeats);
	}
}
