package ca.ulaval.glo4002.GRAISSE;

public class BookingStrategyMaximise extends BookingStrategy {
	private Boardrooms boardrooms;

	public BookingStrategyMaximise(Boardrooms boardrooms) {
		this.boardrooms = boardrooms;
	}

	public void assignBookings(Bookings bookings) {
		bookings.maximiseAssign(boardrooms);
	}
}
