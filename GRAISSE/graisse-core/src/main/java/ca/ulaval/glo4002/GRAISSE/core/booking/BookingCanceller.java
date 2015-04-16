package ca.ulaval.glo4002.GRAISSE.core.booking;

import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingCanceller {
	public void cancelBooking(AssignedBooking assignedBooking);

	public Reservation getBooking(Email email, String iD);

	public boolean hasBooking(Email email, String iD);
}
