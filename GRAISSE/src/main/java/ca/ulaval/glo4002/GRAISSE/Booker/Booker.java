package ca.ulaval.glo4002.GRAISSE.Booker;

import java.util.ArrayList;

import ca.ulaval.glo4002.GRAISSE.Booking.Booking;
import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.Trigger.Trigger;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;

public class Booker {
	
	private Bookings bookings;
	private BookerStrategy bookerStrategy;
	private Boardrooms boardrooms;
	private ArrayList<Trigger> triggers;

	public Booker(BookerStrategy bookerStrategy, Bookings bookings, Boardrooms boardrooms) {
		this.bookings = bookings;
		this.bookerStrategy = bookerStrategy;
		this.boardrooms = boardrooms;
		triggers = new ArrayList<Trigger>();
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
		for(Trigger trigger : triggers) {
			trigger.update(this);
		}
	}
	
	public void registerTrigger(Trigger trigger) {
		if(!triggers.contains(trigger)) {
			triggers.add(trigger);
		}
	}
}
