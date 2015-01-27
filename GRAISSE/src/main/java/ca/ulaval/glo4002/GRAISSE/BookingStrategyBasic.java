package ca.ulaval.glo4002.GRAISSE;

public class BookingStrategyBasic extends BookingStrategy {
	private Boardrooms boardrooms;

	public BookingStrategyBasic(Boardrooms boardrooms) {
		this.boardrooms = boardrooms;
	}

	public void assignBookings(Bookings bookings) {
		bookings.assignBookingToBoardrom(boardrooms);
	}
}