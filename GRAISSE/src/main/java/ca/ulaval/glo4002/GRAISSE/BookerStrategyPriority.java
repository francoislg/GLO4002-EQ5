package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyPriority implements BookerStrategy {

	private BookingsStrategy bookingsStrategy;
	private BoardroomsStrategy boardroomsStrategy;

	public BookerStrategyPriority() {
		bookingsStrategy = new BookingsStrategyBasic();
		boardroomsStrategy = new BoardroomsStrategyBasic();
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assign(boardrooms, bookingsStrategy, boardroomsStrategy);
	}
}
