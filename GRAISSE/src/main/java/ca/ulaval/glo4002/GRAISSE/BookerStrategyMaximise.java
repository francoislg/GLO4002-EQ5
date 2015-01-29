package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyMaximise extends BookerStrategy {

	private BookingsStrategy bookingsStrategy;

	public BookerStrategyMaximise() {
		bookingsStrategy = new BookingsStrategyBasic();
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookingsStrategy.assignBookingsToBoardrooms(this, boardrooms, bookings);
	}
}
