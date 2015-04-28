package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingRepository {

	public void persist(Booking booking);

	public boolean exists(Email email, BookingID bookingID);

	public Booking retrieve(Email email, BookingID bookingID);

	public Collection<Booking> retrieveAll();

	public Collection<Booking> retrieveSortedByPriority();

	public Collection<Booking> getAssignableBookings();

	public Collection<Booking> retrieveAllForEmail(Email email);
}
