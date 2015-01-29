package ca.ulaval.glo4002.GRAISSE;

public class Booker {
	private Bookings bookings;
	private BookerStrategy bookingStrategy;
	private BookerStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;

	public Booker(BookerStrategiesFactory bookingStrategiesFactory, Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookingStrategiesFactory = bookingStrategiesFactory;
		this.boardrooms = boardrooms;

		setStrategyToBasic();
	}

	public void setStrategyToBasic() {
		bookingStrategy = bookingStrategiesFactory.createBasicStrategy();
	}

	public void assignBooking() {
		bookingStrategy.assignBookings(boardrooms, bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}
}
