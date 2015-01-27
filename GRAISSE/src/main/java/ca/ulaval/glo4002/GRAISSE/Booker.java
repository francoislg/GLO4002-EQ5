package ca.ulaval.glo4002.GRAISSE;

public class Booker {
	private Bookings bookings;
	private BookingStrategy bookingStrategy;
	private BookingStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;

	public Booker(BookingStrategiesFactory bookingStrategiesFactory, Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookingStrategiesFactory = bookingStrategiesFactory;
		this.boardrooms = boardrooms;

		setStrategyToBasic();
	}

	public void setStrategyToBasic() {
		bookingStrategy = bookingStrategiesFactory.createBasicStrategy(boardrooms);
	}

	public void assignBooking() {
		bookingStrategy.assignBookings(bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}
}
