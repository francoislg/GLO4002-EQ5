package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

public class Booker extends Observable implements Worker{
	private Bookings bookings;
	private BookingStrategy bookingStrategy;
	private BookingStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;

	public Booker() {
		bookings = new Bookings();
		bookingStrategiesFactory = new BookingStrategiesFactory();
		bookingStrategy = bookingStrategiesFactory
				.setForBasicStrategy(boardrooms);

		boardrooms = new Boardrooms();
	}


	public void assignBooking() {
		bookingStrategy.assignBookings(bookings);
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
	}
	
	public boolean hasWorkToDO() {
		return false;

	}
	public void doWork() {
		
	}

	public int numberOfJobsToDo() {
		return 0;
	}
}
