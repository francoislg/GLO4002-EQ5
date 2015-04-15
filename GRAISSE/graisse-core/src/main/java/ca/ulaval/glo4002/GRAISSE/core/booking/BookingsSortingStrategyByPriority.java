package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookingsSortingStrategyByPriority implements BookingsSortingStrategy {

	public Collection<Booking> sort(BookingRepository bookingRepository) {
		Collection<Booking> bookings = bookingRepository.retrieveSortedByPriority();
		return bookings;
	}
}