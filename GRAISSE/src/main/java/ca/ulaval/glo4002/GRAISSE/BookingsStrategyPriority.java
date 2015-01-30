package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookingsStrategyPriority extends BookingsStrategyBasic {
	public BookingsStrategyPriority() {

	}

	public Collection<Booking> format(Collection<Booking> bookings) {
		Comparator<Booking> byPriorityValue = (booking1, booking2) -> booking1.comparePriorityToBooking(booking2);
		return bookings.stream().sorted(byPriorityValue).collect(Collectors.toList());
	}
}
