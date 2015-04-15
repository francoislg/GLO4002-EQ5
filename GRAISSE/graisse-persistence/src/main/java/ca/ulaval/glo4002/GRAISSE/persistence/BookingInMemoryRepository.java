package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;

public class BookingInMemoryRepository implements BookingRepository {

	private List<Booking> bookings;

	public BookingInMemoryRepository() {
		bookings = new ArrayList<Booking>();
	}

	@Override
	public void persist(Booking booking) {
		if (!bookings.contains(booking)) {
			bookings.add(booking);
		}
	}

	@Override
	public Collection<Booking> retrieveAll() {
		return bookings;
	}

	@Override
	public Collection<Booking> retrieveSortedByPriority() {
		Comparator<Booking> byPriorityValue = (booking1, booking2) -> booking1.comparePriorityToBooking(booking2);
		return getAssignableBookings().stream().sorted(byPriorityValue).collect(Collectors.toList());
	}

	@Override
	public Collection<Booking> getAssignableBookings() {
		Collection<Booking> bookings = this.retrieveAll();
		for (Iterator<Booking> bookingIter = bookings.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (!booking.isAssignable()) {
				bookingIter.remove();
			}
		}
		return bookings;
	}

}