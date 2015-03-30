package ca.ulaval.glo4002.GRAISSE.boardroom;

public class Boardroom implements AssignedBoardroom {

	private int numberOfSeats;
	private String name;

	public Boardroom(String name, int numberOfSeats) {
		this.name = name;
		this.numberOfSeats = numberOfSeats;
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

	public String getName() {
		return name;
	}

	public boolean assign(BookingAssignable bookingToAssign, InterfaceReservationBoardroom interfaceReservationBoardroom) {
		if (verifyNumberOfSeats(bookingToAssign) && interfaceReservationBoardroom.isAvailable(this)) {
			interfaceReservationBoardroom.assign(this, bookingToAssign);
			bookingToAssign.assign();
			return true;
		}
		return false;
	}

	public int compareByNumberOfSeats(Boardroom boardrooomToCompare) {
		return Integer.compare(numberOfSeats, boardrooomToCompare.numberOfSeats);
	}
}
