package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookingsSortingStrategyByPriority implements BookingsSortingStrategy {

	public Collection<Booking> sort(Collection<Booking> bookings) {
		Comparator<Booking> byPriorityValue = (booking1, booking2) -> booking1.comparePriorityToBooking(booking2);
		return bookings.stream().sorted(byPriorityValue).collect(Collectors.toList());
	}
}