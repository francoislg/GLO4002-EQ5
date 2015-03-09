package ca.ulaval.glo4002.GRAISSE.boardroom;

public interface BookingAssignable {

	public abstract void assign();

	public abstract boolean verifyNumberOfSeats(int numberOfSeats);
}