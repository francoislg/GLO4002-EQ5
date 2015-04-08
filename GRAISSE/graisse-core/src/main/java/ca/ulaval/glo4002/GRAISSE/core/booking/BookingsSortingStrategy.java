package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

public interface BookingsSortingStrategy {

	public Collection<Booking> sort(Collection<Booking> bookings);
}
