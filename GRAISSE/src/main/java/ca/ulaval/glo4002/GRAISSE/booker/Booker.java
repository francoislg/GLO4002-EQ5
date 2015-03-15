package ca.ulaval.glo4002.GRAISSE.booker;

import java.util.ArrayList;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;

public class Booker {
	
	private Bookings bookings;
	private BookerStrategy bookerStrategy;
	private Boardrooms boardrooms;
	private ArrayList<BookerFinishedAssigningTrigger> triggers;

	public Booker(BookerStrategy bookerStrategy, Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookerStrategy = bookerStrategy;
		this.boardrooms = boardrooms;
		triggers = new ArrayList<BookerFinishedAssigningTrigger>();
	}

	public void assignBookings() {
		bookerStrategy.assignBookings(boardrooms, bookings);
		notifyTriggers();
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.add(bookingToAdd);
		notifyTriggers();
	}

	public boolean hasBookingsToAssign() {
		return !bookings.isEmpty();
	}

	public int numberOfBookingsToAssign() {
		return bookings.getSize();
	}

	private void notifyTriggers() {
		for(BookerFinishedAssigningTrigger trigger : triggers) {
			trigger.update(this);
		}
	}
	
	public void registerTrigger(BookerFinishedAssigningTrigger trigger) {
		if(!triggers.contains(trigger)) {
			triggers.add(trigger);
		}
	}
}
