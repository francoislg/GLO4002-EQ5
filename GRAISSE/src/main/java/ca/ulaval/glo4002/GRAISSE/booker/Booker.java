package ca.ulaval.glo4002.GRAISSE.booker;

import java.util.ArrayList;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;

public class Booker {

	private Bookings bookings;
	private BookerStrategy bookerStrategy;
	private Boardrooms boardrooms;
	private ArrayList<BookerTrigger> triggers;

	public Booker(BookerStrategy bookerStrategy, Bookings bookings, Boardrooms boardrooms) {
		this.bookerStrategy = bookerStrategy;
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		triggers = new ArrayList<BookerTrigger>();
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
		bookings.cancelBooking(booking);
	}

	public boolean hasBookingsToAssign() {
		return bookings.hasUnassignedBookings();
	}

	public int numberOfBookingsToAssign() {
		return bookings.getNumberOfUnassignedBookings();
	}

	private void notifyTriggers() {
		for (BookerTrigger trigger : triggers) {
			trigger.update(this);
		}
	}

	public void registerTrigger(BookerTrigger trigger) {
		if (!triggers.contains(trigger)) {
			triggers.add(trigger);
		}
	}
}
