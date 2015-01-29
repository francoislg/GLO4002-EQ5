package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;

public abstract class BookingsStrategy {

	public abstract Collection<Booking> format(Collection<Booking> bookings);
}
