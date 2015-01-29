package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyPriority extends BookerStrategy {

	public BookerStrategyPriority() {

	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.priorityAssign(boardrooms);
	}
}
