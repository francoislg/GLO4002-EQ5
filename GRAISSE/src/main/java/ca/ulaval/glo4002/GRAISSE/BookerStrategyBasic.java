package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategyBasic extends BookerStrategy {

	public BookerStrategyBasic() {

	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.basicAssign(boardrooms);
	}

}