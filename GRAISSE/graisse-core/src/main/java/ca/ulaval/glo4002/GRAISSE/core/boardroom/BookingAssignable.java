package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean hasPromoter(Email email);

	public boolean isAssigned();

	public void cancel();

	public Collection<Email> getParticipantsEmail();

	void refuse();

	public BookingState getState();

	public String getPromoterEmail();

	public int getNumberOfSeats();

	public String getID();
}