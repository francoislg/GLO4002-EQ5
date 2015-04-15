package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class Booking implements BookingAssignable, AssignedBooking {

	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private int numberOfSeatsNeeded;
	private boolean assigned;
	private boolean canceled;
	private Priority priority;
	private User creator;
	private List<Email> participants;

	public Booking(User creator, int numberOfSeatsNeeded) {
		this(creator, numberOfSeatsNeeded, DEFAULT_PRIORITY);
	}

	public Booking(User creator, int numberOfSeatsNeeded, Priority priority) {
		this.creator = creator;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = priority;
		assigned = false;
		canceled = false;
		participants = new ArrayList<Email>();
	}

	public void assign() {
		assigned = true;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void cancel() {
		canceled = true;
	}

	public boolean hasCreator(User user) {
		return creator.equals(user);
	}

	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}

	public void addParticipant(Email email) {
		participants.add(email);
	}

	@Override
	public Collection<Email> getParticipantsEmail() {
		return participants;
	}
}