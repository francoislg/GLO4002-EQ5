package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;

public class Bookings {
	
	private Collection<Booking> bookingList;

	public Bookings() {
		bookingList = new ArrayList<Booking>();
	}

	public void add(Booking booking) {
		bookingList.add(booking);
	}

	public void remove(BookingAssignable booking) {
		bookingList.remove(booking);
	}

	public boolean isEmpty() {
		return bookingList.isEmpty();
	}

	public void assignBookingsToBoardrooms(Boardrooms boardrooms, BookingsStrategy bookingsStrategy, BoardroomsStrategy boardroomsStrategy) {
		Collection<Booking> formatedBookingList = bookingsStrategy.sort(bookingList);
		for (BookingAssignable booking : formatedBookingList) {
			boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy);
		}
	}

	public int getSize() {
		return bookingList.size();
	}
}
