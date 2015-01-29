package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookingsStrategyMaximise implements BookingsStrategy {

	public Collection<Booking> format(Collection<Booking> bookings) {
		Comparator<Booking> byNumberOfSeats = (booking1, booking2) -> booking1.compareNumberOfSeatsToBooking(booking2);
		return bookings.stream().sorted(byNumberOfSeats).collect(Collectors.toList());
	}
}
