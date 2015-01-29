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

	public Collection<Booking> findAll() {
		return bookingList;
	}

	public void assign(Boardrooms boardrooms, BookingsStrategy bookingsStrategy, BoardroomsStrategy boardroomsStrategy) {
		Collection<Booking> formatedBookingList = bookingsStrategy.format(bookingList);
		for (Iterator<Booking> bookingIter = formatedBookingList.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy)) {
				bookingIter.remove();
			}
		}
	}
}
