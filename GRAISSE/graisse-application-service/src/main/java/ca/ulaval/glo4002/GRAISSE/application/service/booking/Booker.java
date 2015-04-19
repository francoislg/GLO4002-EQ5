package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Observer;

public class Booker {

	private Bookings bookings;
	private Boardrooms boardrooms;
	private BookerStrategy bookerStrategy;
	private Reservations reservations;
	private List<Observer<Booker>> observers;

	public Booker(Bookings bookings, Boardrooms boardrooms, Reservations reservations) {
		this.bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyDefault());
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		this.reservations = reservations;
		observers = new ArrayList<Observer<Booker>>();
	}

	public void assignBookings() {
		bookerStrategy.assignBookings(reservations);
		notifyTriggers();
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.add(bookingToAdd);
		notifyTriggers();
	}

	public void setBookerStrategy(BookerStrategy bookerStrategy) {
		this.bookerStrategy = bookerStrategy;
	}

	public boolean hasBookingsToAssign() {
		return bookings.hasAssignableBookings();
	}

	public int numberOfBookingsToAssign() {
		return bookings.getNumberOfAssignableBookings();
	}

	private void notifyTriggers() {
		for (Observer<Booker> observer : observers) {
			observer.update(this);
		}
	}

	public void registerTrigger(Observer<Booker> observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}
}
