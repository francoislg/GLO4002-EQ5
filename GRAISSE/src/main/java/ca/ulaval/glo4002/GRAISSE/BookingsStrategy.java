package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;

public interface BookingsStrategy {

	public Collection<Booking> format(Collection<Booking> bookings);
}