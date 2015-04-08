package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

public class BookingsSortingStrategyDefault implements BookingsSortingStrategy {

	public Collection<Booking> sort(Collection<Booking> bookings) {
		return bookings;
	}
}