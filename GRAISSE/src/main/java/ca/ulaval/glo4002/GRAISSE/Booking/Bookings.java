package ca.ulaval.glo4002.GRAISSE.Booking;

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
		for (Iterator<Booking> bookingIter = formatedBookingList.iterator(); bookingIter.hasNext();) {
			BookingAssignable booking = bookingIter.next();
			if (boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy)) {
				bookingIter.remove();
			}
		}
	}

	public int getSize() {
		return bookingList.size();
	}
}
