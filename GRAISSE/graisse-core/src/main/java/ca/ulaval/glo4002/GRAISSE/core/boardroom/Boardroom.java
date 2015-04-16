package ca.ulaval.glo4002.GRAISSE.core.boardroom;

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

	public void assign(BookingAssignable bookingToAssign, ReservationAssigner reservationAssigner) {
		reservationAssigner.assign(this, bookingToAssign);
	}

	public int compareByNumberOfSeats(Boardroom boardrooomToCompare) {
		return Integer.compare(numberOfSeats, boardrooomToCompare.numberOfSeats);
	}

	public boolean canAssign(BookingAssignable bookingToAssign, ReservationAssigner reservationAssigner) {
		if (verifyNumberOfSeats(bookingToAssign) && reservationAssigner.isAvailable(this)) {
			return true;
		}
		return false;
	}
}
