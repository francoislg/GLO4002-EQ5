package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

public class BookingsSortingStrategyByPriority implements BookingsSortingStrategy {

	public Collection<Booking> sort(BookingRepository bookingRepository) {
		Collection<Booking> bookings = bookingRepository.retrieveSortedByPriority();
		return bookings;
	}
}