package ca.ulaval.glo4002.GRAISSE.core.booking;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingCanceller {
	public void cancelBooking(AssignedBooking assignedBooking);

	public BookingDTO retrieveReservation(Email email, BookingID bookingID);

	public boolean hasReservation(Email email, BookingID bookingID);
}
