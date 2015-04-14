package ca.ulaval.glo4002.GRAISSE.core.boardroom;
import ca.ulaval.glo4002.GRAISSE.core.user.User;


public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean hasPromoter(User user);

	public boolean isAssigned();

	public void cancel();
}