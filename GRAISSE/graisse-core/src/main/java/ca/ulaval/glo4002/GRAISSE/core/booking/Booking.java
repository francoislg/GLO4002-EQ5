package ca.ulaval.glo4002.GRAISSE.core.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.user.User;


public class Booking implements BookingAssignable, AssignedBooking {

	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;
	
	private String name;
	private int numberOfSeatsNeeded;
	private BookingState state;
	private Priority priority;
	private User promoter;

	public Booking(User promoter, String name, int numberOfSeatsNeeded) {
		this(promoter, name, numberOfSeatsNeeded, DEFAULT_PRIORITY);
	}

	public Booking(User promoter, String name, int numberOfSeatsNeeded, Priority priority) {
		this.promoter = promoter;
		this.name = name;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = priority;
		this.state = BookingState.WAITING;
	}

	public void assign() {
		state = BookingState.ASSIGNED;
	}

	public boolean isAssigned() {
		return state == BookingState.ASSIGNED;
	}

	public void cancel() {
		state = BookingState.CANCELLED;
	}
	
	public void refuse(){
		state = BookingState.REFUSED;
	}

	public boolean hasPromoter(User user) {
		return promoter.equals(user);
	}
	
	public boolean hasName(String name){
		return this.name.equals(name);
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}
}