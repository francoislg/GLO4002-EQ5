package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class Booking implements BookingAssignable, AssignedBooking {

	private static final Priority DEFAULT_PRIORITY = Priority.MEDIUM;

	private BookingID ID;
	private int numberOfSeatsNeeded;
	private BookingState state;
	private Priority priority;
	private User promoter;
	private List<Email> participants;

	public Booking(User promoter, int numberOfSeatsNeeded) {
		this(promoter, numberOfSeatsNeeded, DEFAULT_PRIORITY);
	}

	public Booking(User promoter, int numberOfSeatsNeeded, Priority priority) {
		this.ID = new BookingID();
		this.promoter = promoter;
		this.numberOfSeatsNeeded = numberOfSeatsNeeded;
		this.priority = priority;
		this.state = BookingState.WAITING;
		participants = new ArrayList<Email>();
	}

	@Override
	public void assign() {
		state = BookingState.ASSIGNED;
	}

	@Override
	public boolean isAssigned() {
		return state == BookingState.ASSIGNED;
	}

	public boolean isAssignable() {
		return state == BookingState.WAITING;
	}

	@Override
	public void cancel() {
		state = BookingState.CANCELLED;
	}

	@Override
	public void refuse() {
		state = BookingState.REFUSED;
	}

	@Override
	public boolean hasPromoter(Email email) {
		return promoter.hasEmail(email);
	}

	@Override
	public boolean verifyNumberOfSeats(int numberOfSeats) {
		return numberOfSeatsNeeded <= numberOfSeats;
	}

	public int comparePriorityToBooking(Booking bookingToCompare) {
		return priority.compare(bookingToCompare.priority);
	}

	public void addParticipant(Email email) {
		if(!participants.contains(email)) {
			participants.add(email);
		}
	}

	@Override
	public Collection<Email> getParticipantsEmail() {
		return participants;
	}

	public int getNumberOfSeats() {
		return numberOfSeatsNeeded;
	}

	public String getPromoterEmail() {
		return promoter.getEmail().getValue();
	}

	public BookingState getState() {
		return state;
	}

	@Override
	public BookingID getID() {
		return ID;
	}

	@Override
	public boolean hasID(BookingID bookingID) {
		return ID.equals(bookingID);
	}
}