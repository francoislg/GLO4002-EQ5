package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Bookings {
	private Collection<Booking> bookingList;

	public Bookings() {
		bookingList = new ArrayList<Booking>();
	}

	public void addBooking(Booking booking) {
		bookingList.add(booking);
	}

	public void removeBooking(Booking booking) {
		bookingList.remove(booking);
	}

	public boolean isEmpty() {
		return bookingList.isEmpty();
	}

	public int getBookingsSize() {
		return bookingList.size();
	}

	public void assignBookingToBoardrom(Boardrooms boardrooms) {
		for (Iterator<Booking> bookingIter = bookingList.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (boardrooms.assignToBoardroom(booking)) {
				bookingIter.remove();
			}
		}
	}
}
