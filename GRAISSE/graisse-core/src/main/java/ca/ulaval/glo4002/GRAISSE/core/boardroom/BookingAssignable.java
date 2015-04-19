package ca.ulaval.glo4002.GRAISSE.core.boardroom;


public interface BookingAssignable {

	public void assign();

	public boolean verifyNumberOfSeats(int numberOfSeats);

	public boolean isAssigned();

	public void cancel();

	void refuse();
}