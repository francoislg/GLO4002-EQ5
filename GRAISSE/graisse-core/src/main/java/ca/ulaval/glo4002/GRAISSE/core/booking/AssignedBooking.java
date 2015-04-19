package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface AssignedBooking {

	public Email getPromoterEmail();

	public BookingState getState();

	public int getNumberOfSeats();

	public BookingID getID();

	public boolean hasID(BookingID bookingID);

	public boolean hasPromoter(Email promoter);

	public Collection<Email> getParticipantsEmail();

	public boolean isAssigned();
}
