package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

public class Booker extends Observable implements Worker {
	private Bookings bookings;
	private BookingStrategy bookingStrategy;
	private BookingStrategiesFactory bookingStrategiesFactory;
	private Boardrooms boardrooms;


	public Booker(BookingStrategiesFactory bookingStrategiesFactory, Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookingStrategiesFactory = bookingStrategiesFactory;
		this.boardrooms = boardrooms;
	}
	
	public Booker() {
		bookings = new Bookings();
		bookingStrategiesFactory = new BookingStrategiesFactory();
		bookingStrategy = bookingStrategiesFactory.createBasicStrategy(boardrooms);


		setStrategyToBasic();
	}

	public void setStrategyToBasic() {
		bookingStrategy = bookingStrategiesFactory.createBasicStrategy(boardrooms);
	}

	public void assignBookings() {
		bookingStrategy.assignBookings(bookings);
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
		return 0;
	}
}
