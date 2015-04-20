package ca.ulaval.glo4002.GRAISSE.core.shared;

import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;

public interface Notifyer<T> {

	public void notify(T object);

	void notify(AssignedBooking booking);
}
