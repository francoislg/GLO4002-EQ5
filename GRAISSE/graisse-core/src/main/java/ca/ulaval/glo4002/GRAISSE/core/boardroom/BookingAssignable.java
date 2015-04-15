package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean hasPromoter(Email email);

	public boolean isAssigned();

	public void cancel();

	boolean hasName(String name);

	void refuse();
}