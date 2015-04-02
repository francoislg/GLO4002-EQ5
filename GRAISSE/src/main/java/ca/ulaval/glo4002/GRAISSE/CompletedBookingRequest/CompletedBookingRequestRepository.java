package ca.ulaval.glo4002.GRAISSE.completedBookingRequest;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public interface CompletedBookingRequestRepository {
	public void persist(CompletedBookingRequest assignedBooking);

	public void remove(CompletedBookingRequest assignedBooking);

	public CompletedBookingRequest retrieve(AssignedBooking assignedBooking) throws CompletedBookingRequestNotFoundException;

	public Collection<CompletedBookingRequest> retrieveAll();

	public boolean existsWithBoardroom(Boardroom boardroom);
}
