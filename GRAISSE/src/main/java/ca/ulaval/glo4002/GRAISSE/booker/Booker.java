package ca.ulaval.glo4002.GRAISSE.booker;

import java.util.ArrayList;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategyDefault;

public class Booker {

	private Bookings bookings;
	private Boardrooms boardrooms;
	private BookerStrategy bookerStrategy;
	private InterfaceReservationBooking interfaceReservationBooking;
	private ArrayList<BookerTrigger> triggers;

	public Booker(Bookings bookings, Boardrooms boardrooms, InterfaceReservationBooking interfaceReservationBooking) {
		this.bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyDefault());
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		this.interfaceReservationBooking = interfaceReservationBooking;
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
		interfaceReservationBooking.cancelBooking(booking);
	}

	public void setBookerStrategy(BookerStrategy bookerStrategy) {
		this.bookerStrategy = bookerStrategy;
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
