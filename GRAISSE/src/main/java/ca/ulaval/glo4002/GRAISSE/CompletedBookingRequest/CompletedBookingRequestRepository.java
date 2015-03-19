package ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public interface CompletedBookingRequestRepository {
	public void persist(CompletedBookingRequest assignedBooking);

	public CompletedBookingRequest retrieve(AssignedBooking assignedBooking) throws CompletedBookingRequestNotFoundException;

	public Collection<CompletedBookingRequest> retrieveAll();
}
