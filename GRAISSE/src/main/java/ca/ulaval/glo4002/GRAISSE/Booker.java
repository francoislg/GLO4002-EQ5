package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

public class Booker extends Observable implements Worker {
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

	public void assignBookings() {
		bookingStrategy.assignBookings(boardrooms, bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}

	public boolean hasWorkToDO() {
		return bookings.isEmpty();

	}

	public void doWork() {
		assignBookings();
	}

	public int numberOfJobsToDo() {
		return bookings.getBookingsSize();

	}
}
