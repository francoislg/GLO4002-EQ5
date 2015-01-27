package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

public class Booker extends Observable implements Worker {
	private Bookings bookings;
	private BookingStrategy bookingStrategy;
	private BookingStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;

	public Booker() {
		bookings = new Bookings();
		bookingStrategiesFactory = new BookingStrategiesFactory();
		bookingStrategy = bookingStrategiesFactory.setForBasicStrategy(boardrooms);

		boardrooms = new Boardrooms();
	}

	public void assignBookings() {
		bookingStrategy.assignBookings(bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}

	public boolean hasWorkToDO() {
		return 0 < bookings.getAllBooking().size();

	}

	public void doWork() {
		assignBookings();
	}

	public int numberOfJobsToDo() {
		return bookings.getAllBooking().size();
	}
}
