package ca.ulaval.glo4002.GRAISSE;

public class BookingStrategyPriority extends BookingStrategy {
	private Boardrooms boardrooms;

	public BookingStrategyPriority(Boardrooms boardrooms) {
		this.boardrooms = boardrooms;
	}

	public void assignBookings(Bookings bookings) {
		bookings.priorityAssign(boardrooms);
	}
}
