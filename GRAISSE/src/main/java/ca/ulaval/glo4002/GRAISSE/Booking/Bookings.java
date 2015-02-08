package ca.ulaval.glo4002.GRAISSE.Booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BookingAssignable;

public class Bookings {
	private Collection<Booking> bookingList;

	public Bookings() {
		bookingList = new ArrayList<Booking>();
	}

	public void addBooking(Booking booking) {
		bookingList.add(booking);
	}

	public void removeBooking(BookingAssignable booking) {
		bookingList.remove(booking);
	}

	public boolean isEmpty() {
		return bookingList.isEmpty();
	}

	public void assignBookingsToBoardrooms(Boardrooms boardrooms, BookingsStrategy bookingsStrategy, BoardroomsStrategy boardroomsStrategy) {
		Collection<Booking> formatedBookingList = bookingsStrategy.format(bookingList);
		for (Iterator<Booking> bookingIter = formatedBookingList.iterator(); bookingIter.hasNext();) {
			BookingAssignable booking = bookingIter.next();
			if (boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy)) {
				bookingIter.remove();
			}
		}
	}

	public int getBookingsSize() {
		return bookingList.size();
	}
}
