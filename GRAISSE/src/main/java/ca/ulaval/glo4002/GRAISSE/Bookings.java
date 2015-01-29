package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public void basicAssign(Boardrooms boardrooms) {
		for (Iterator<Booking> bookingIter = bookingList.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (boardrooms.assignBookingToBoardroom(booking)) {
				bookingIter.remove();
			}
		}
	}

	public void maximiseAssign(Boardrooms boardrooms) {
		for (Iterator<Booking> bookingIter = bookingList.iterator(); bookingIter.hasNext();) {
			Booking bookingToAssign = bookingIter.next();
			if (boardrooms.assignBookingToMinSeatsBoardroom(bookingToAssign)) {
				bookingIter.remove();
			}
		}
	}

	public void priorityAssign(Boardrooms boardrooms) {
		Collection<Booking> bookings = getStreamSortByPriorityValue().collect(Collectors.toList());

		for (Iterator<Booking> bookingIter = bookings.iterator(); bookingIter.hasNext();) {
			Booking bookingToAssign = bookingIter.next();
			if (boardrooms.assignBookingToBoardroom(bookingToAssign)) {
				bookingIter.remove();
			}
		}
	}

	private Stream<Booking> getStreamSortByPriorityValue() {
		Comparator<Booking> byPriorityValue = (e1, e2) -> Integer.compare(e1.getPriorityValue(), e2.getPriorityValue());
		return bookingList.stream().sorted(byPriorityValue);
	}
}
