package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.ArrayList;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.booker.BookerFinishedAssigningTrigger;
import ca.ulaval.glo4002.GRAISSE.user.User;

public class Booking implements BookingAssignable {	
	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private Priority priority;
	private User creator;
	private ArrayList<BookingAssignedTrigger> triggers;

	public Booking(User creator, int numberOfSeatsNeeded) {
		this.creator = creator;
		this.assigned = false;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = DEFAULT_PRIORITY;
		this.triggers = new ArrayList<BookingAssignedTrigger>();
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void assign() {
		assigned = true;
		notifyTriggers();
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
	
	public void notifyTriggers(){
		for(BookingAssignedTrigger trigger : triggers) {
			trigger.update(this);
		}
	}
	
	public void registerBookingAssignedTrigger(BookingAssignedTrigger trigger) {
		if(!triggers.contains(trigger)) {
			triggers.add(trigger);
		}
	}
}
