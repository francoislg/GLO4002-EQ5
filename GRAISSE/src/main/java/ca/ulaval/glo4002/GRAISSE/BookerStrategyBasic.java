package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyBasic extends BookerStrategy {

	private BookingsStrategy bookingsStrategy;
	private BoardroomsStrategy boardroomsStrategy;

	public BookerStrategyBasic() {
		bookingsStrategy = new BookingsStrategyBasic();
		boardroomsStrategy = new BoadroomsStrategyBasic();
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assign(boardrooms, bookingStrategy, boardroomStrategy);
	}

}