package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.bookingExceptions.InvalidPriorityException;

public class Booking implements BookingAssignable {
	
	public enum Priority {
							VERY_LOW,
							LOW,
							MEDIUM,
							HIGH,
							VERY_HIGH
						  }
	
	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private Priority priority;

	public Booking(int numberOfSeatsNeeded) {
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		priority = DEFAULT_PRIORITY;
	}
	
	public void setPriority(Priority priority) throws InvalidPriorityException{
		this.priority = priority;
	}

	public void assign() {
		assigned = true;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}


	public int comparePriorityToBooking(Booking bookingToCompare) {
		
		int actualValue= priority.ordinal(); 
		int valueToCompare= bookingToCompare.priority.ordinal();  
		
		return  Integer.compare(actualValue, valueToCompare);

	}
}
