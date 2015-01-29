package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyMaximise implements BookerStrategy {

	private BookingsStrategy bookingsStrategy;
	private BoardroomsStrategy boardroomsStrategy;

	public BookerStrategyMaximise() {
		bookingsStrategy = new BookingsStrategyBasic();
		boardroomsStrategy = new BoardroomsStrategyBasic();
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assign(boardrooms, bookingsStrategy, boardroomsStrategy);
	}
}
