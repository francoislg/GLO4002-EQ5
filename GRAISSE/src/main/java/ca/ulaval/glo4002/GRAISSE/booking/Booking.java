package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.user.User;

public class Booking implements BookingAssignable {
	
	public enum Priority {
		VERY_LOW(0),
		LOW(1),
		MEDIUM(2),
		HIGH(3),
		VERY_HIGH(4);
		
		private final int value;
		
		private Priority(int value) {
			this.value = value;
		}
		
		public int compare(Priority priorityToCompare) {
			return  Integer.compare(value, priorityToCompare.value);
		}
	}
	
	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private Priority priority;
	private User creator;

	public Booking(User creator, int numberOfSeatsNeeded) {
		this.creator = creator;
		assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		priority = DEFAULT_PRIORITY;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void assign() {
		assigned = true;
	}
	
	public boolean isAssigned() {
		return assigned;
	}
	
	public boolean hasCreator(User user){
		return creator.equals(user);
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}
}
