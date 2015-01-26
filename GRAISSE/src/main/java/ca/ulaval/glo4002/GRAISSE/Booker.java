package ca.ulaval.glo4002.GRAISSE;

public class Booker {
	private Bookings bookings;
	private BookingStrategy bookingStrategy;
	private BookingStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;

	public Booker(BookingStrategiesFactory bookingStrategiesFactory,
			Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookingStrategiesFactory = bookingStrategiesFactory;
		bookingStrategy = bookingStrategiesFactory
				.setForBasicStrategy(boardrooms);

		this.boardrooms = boardrooms;
	}

	public void setStrategyForBasic() {
		bookingStrategy = bookingStrategiesFactory
				.setForBasicStrategy(boardrooms);
	}

	public void assignBooking() {
		bookingStrategy.assignBookings(bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}
}
