package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.ReservedBoardroom;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.ReservedBoadroomNotFoundException;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.ReservedBoardroomRepository;

public class CompletedBookingRequestInMemoryRepository implements ReservedBoardroomRepository {

	private List<ReservedBoardroom> completedBookingRequests = new ArrayList<ReservedBoardroom>();

	private boolean completedBookingRequestNotAlreadyInMemory(ReservedBoardroom completedBookingRequest) {
		return !completedBookingRequests.contains(completedBookingRequest);
	}

	@Override
	public void persist(ReservedBoardroom completedBookingRequest) {
		if (completedBookingRequestNotAlreadyInMemory(completedBookingRequest)) {
			completedBookingRequests.add(completedBookingRequest);
		}

	}

	@Override
	public Collection<ReservedBoardroom> retrieveAll() {
		return completedBookingRequests;
	}

	@Override
	public ReservedBoardroom retrieve(AssignedBooking assignedBooking) throws ReservedBoadroomNotFoundException {
		for (ReservedBoardroom completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBooking(assignedBooking)) {
				return completedBookingRequest;
			}
		}
		throw new ReservedBoadroomNotFoundException();
	}

	@Override
	public boolean existsWithBoardroom(Boardroom boardroom) {
		for (ReservedBoardroom completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(ReservedBoardroom completedBookingRequest) {
		completedBookingRequests.remove(completedBookingRequest);

	}
}
