package ca.ulaval.glo4002.GRAISSE.boardroom;

import ca.ulaval.glo4002.GRAISSE.user.User;

public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean hasCreator(User user);
}