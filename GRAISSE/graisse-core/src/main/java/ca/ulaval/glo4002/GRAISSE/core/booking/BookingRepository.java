package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

public interface BookingRepository {

	public void persist(Booking booking);

	public Collection<Booking> retrieveAll();
}