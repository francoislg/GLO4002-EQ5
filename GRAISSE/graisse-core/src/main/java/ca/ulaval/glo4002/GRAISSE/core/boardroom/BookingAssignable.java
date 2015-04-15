package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean hasCreator(User user);

	public boolean isAssigned();

	public void cancel();

	public Collection<Email> getParticipantsEmail();
}