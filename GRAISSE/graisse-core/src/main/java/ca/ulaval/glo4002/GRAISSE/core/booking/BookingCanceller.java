package ca.ulaval.glo4002.GRAISSE.core.booking;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingCanceller {
	public void cancelBooking(AssignedBooking assignedBooking);

	public BookingDTO retrieveReservation(Email email, String iD);

	public boolean hasReservation(Email email, String iD);
}
