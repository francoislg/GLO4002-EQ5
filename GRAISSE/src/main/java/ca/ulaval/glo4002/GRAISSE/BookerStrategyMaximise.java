package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyMaximise extends BookerStrategy {

	public BookerStrategyMaximise() {

	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.maximiseAssign(boardrooms);
	}
}
