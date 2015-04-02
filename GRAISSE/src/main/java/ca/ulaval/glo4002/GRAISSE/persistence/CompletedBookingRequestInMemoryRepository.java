package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest.CompletedBookingRequest;
import ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest.CompletedBookingRequestNotFoundException;
import ca.ulaval.glo4002.GRAISSE.CompletedBookingRequest.CompletedBookingRequestRepository;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public class CompletedBookingRequestInMemoryRepository implements CompletedBookingRequestRepository {

	private List<CompletedBookingRequest> completedBookingRequests = new ArrayList<CompletedBookingRequest>();

	private boolean completedBookingRequestNotAlreadyInMemory(CompletedBookingRequest completedBookingRequest) {
		return !completedBookingRequests.contains(completedBookingRequest);
	}

	@Override
	public void persist(CompletedBookingRequest completedBookingRequest) {
		if (completedBookingRequestNotAlreadyInMemory(completedBookingRequest)) {
			completedBookingRequests.add(completedBookingRequest);
		}

	}

	@Override
	public Collection<CompletedBookingRequest> retrieveAll() {
		return completedBookingRequests;
	}

	@Override
	public CompletedBookingRequest retrieve(AssignedBooking assignedBooking) throws CompletedBookingRequestNotFoundException {
		for (CompletedBookingRequest completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBooking(assignedBooking)) {
				return completedBookingRequest;
			}
		}
		throw new CompletedBookingRequestNotFoundException();
	}

	@Override
	public boolean existsWithBoardroom(Boardroom boardroom) {
		for (CompletedBookingRequest completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(CompletedBookingRequest completedBookingRequest) {
		completedBookingRequests.remove(completedBookingRequest);

	}
}
