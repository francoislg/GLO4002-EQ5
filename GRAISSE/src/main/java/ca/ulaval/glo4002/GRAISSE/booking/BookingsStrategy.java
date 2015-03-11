package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.Collection;

public interface BookingsStrategy {

	public Collection<Booking> sort(Collection<Booking> bookings);
}
