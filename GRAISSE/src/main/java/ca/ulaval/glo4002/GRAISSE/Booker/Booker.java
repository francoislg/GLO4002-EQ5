package ca.ulaval.glo4002.GRAISSE.Booker;

import java.util.Observable;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Booking.Booking;
import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;

public class Booker extends Observable {
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
		notifyObserversThatBookerHasChanged();
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.addBooking(bookingToAdd);
		notifyObserversThatBookerHasChanged();
	}

	public boolean hasBookingsToAssign() {
		return bookings.isEmpty();
	}

	public int numberOfJobsToDo() {
		return bookings.getBookingsSize();
	}

	protected void notifyObserversThatBookerHasChanged() {
		setChanged();
		notifyObservers();
		clearChanged();
	}
}
