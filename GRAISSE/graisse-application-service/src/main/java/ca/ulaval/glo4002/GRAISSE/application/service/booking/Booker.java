package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.booking.InterfaceReservationBooking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Observer;

public class Booker {

	private Bookings bookings;
	private Boardrooms boardrooms;
	private BookerStrategy bookerStrategy;
	private InterfaceReservationBooking interfaceReservationBooking;
	private List<Observer<Booker>> observers;

	public Booker(Bookings bookings, Boardrooms boardrooms, InterfaceReservationBooking interfaceReservationBooking) {
		this.bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyDefault());
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		this.interfaceReservationBooking = interfaceReservationBooking;
		observers = new ArrayList<Observer<Booker>>();
	}

	public void assignBookings() {
		bookerStrategy.assignBookings(boardrooms, bookings);
		notifyTriggers();
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.add(bookingToAdd);
		notifyTriggers();
	}

	public void cancelBooking(Booking booking) {
		interfaceReservationBooking.cancelBooking(booking);
	}

	public void setBookerStrategy(BookerStrategy bookerStrategy) {
		this.bookerStrategy = bookerStrategy;
	}

	public boolean hasBookingsToAssign() {
		return bookings.hasAssignableBookings();
	}

	public int numberOfBookingsToAssign() {
		return bookings.getNumberAssignableBookings();
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
