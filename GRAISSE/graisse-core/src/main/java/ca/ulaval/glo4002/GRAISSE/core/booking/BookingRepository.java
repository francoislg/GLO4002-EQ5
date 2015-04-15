package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingRepository {

	public void persist(Booking booking);

	public Collection<Booking> retrieveAll();

	public Booking retrieveBooking(Email promoter, String name);
}
