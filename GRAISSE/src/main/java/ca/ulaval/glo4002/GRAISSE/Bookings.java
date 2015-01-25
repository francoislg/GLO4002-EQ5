package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.Collection;

public class Bookings {
	private Collection<Booking> bookingList;

	public Bookings() {
		bookingList = new ArrayList();
	}

	public void addBooking(Booking booking) {
		bookingList.add(booking);
	}

	public void removeBooking(Booking booking) {
		bookingList.remove(booking);
	}

	public Collection<Booking> getAllBooking() {
		return bookingList;
	}

	public void assign(Boardrooms boardrooms) {
		for (Booking booking : bookingList) {
			if (boardrooms.assignToBoardroom(booking)) {
				removeBooking(booking);
			}
		}

	}
}
