package ca.ulaval.glo4002.GRAISSE.Booking;

import ca.ulaval.glo4002.GRAISSE.Boardroom.BookingAssignable;

public class Booking implements BookingAssignable {
	
	public enum Priority {
							VERY_LOW,
							LOW,
							MEDIUM,
							HIGH,
							VERY_HIGH
						  }
	
	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;
	private static final int A_POSITIVE_NUMBER =1;

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

	@Override
	public void assign() {
		assigned = true;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

	@Override
	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return this.numberOfSeatsNeeded <= numberOfSeats;
	}


	public int comparePriorityToBooking(Booking bookingToCompare) {
		
		int comparisonValue=0; 
		
		if(priority == bookingToCompare.priority) return 0;
		
		switch(bookingToCompare.priority)
		{
			case VERY_LOW :
				if(priority == Priority.LOW) comparisonValue= A_POSITIVE_NUMBER;
				else comparisonValue= -A_POSITIVE_NUMBER;
			break;
			
			case LOW :
				if(priority == Priority.VERY_LOW) comparisonValue= -A_POSITIVE_NUMBER;
				else comparisonValue= A_POSITIVE_NUMBER;
			break;
			
			case MEDIUM :
				if((priority == Priority.VERY_LOW)|| (priority == Priority.LOW) ) comparisonValue= -A_POSITIVE_NUMBER;
				else comparisonValue= A_POSITIVE_NUMBER;
			break;
			
			case HIGH :
				if(priority == Priority.VERY_HIGH) comparisonValue= A_POSITIVE_NUMBER;
				else comparisonValue= -A_POSITIVE_NUMBER;
			break;
			
			case VERY_HIGH :comparisonValue= -A_POSITIVE_NUMBER;;
			break;
		}
		
		return comparisonValue;
	}
}
