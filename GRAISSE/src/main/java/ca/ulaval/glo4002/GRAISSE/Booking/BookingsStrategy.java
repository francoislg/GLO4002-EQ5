package ca.ulaval.glo4002.GRAISSE.Booking;

import java.util.Collection;

public interface BookingsStrategy {

	public Collection<Booking> sort(Collection<Booking> bookings);
}
