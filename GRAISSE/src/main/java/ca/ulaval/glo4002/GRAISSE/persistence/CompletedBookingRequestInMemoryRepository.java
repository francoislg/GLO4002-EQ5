package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.reservedBoardroom.ReservationRepository;

public class CompletedBookingRequestInMemoryRepository implements ReservationRepository {

	private List<Reservation> completedBookingRequests = new ArrayList<Reservation>();

	private boolean completedBookingRequestNotAlreadyInMemory(Reservation completedBookingRequest) {
		return !completedBookingRequests.contains(completedBookingRequest);
	}

	@Override
	public void persist(Reservation completedBookingRequest) {
		if (completedBookingRequestNotAlreadyInMemory(completedBookingRequest)) {
			completedBookingRequests.add(completedBookingRequest);
		}

	}

	@Override
	public Collection<Reservation> retrieveAll() {
		return completedBookingRequests;
	}

	@Override
	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException {
		for (Reservation completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBooking(assignedBooking)) {
				return completedBookingRequest;
			}
		}
		throw new ReservationNotFoundException();
	}

	@Override
	public boolean existsWithBoardroom(Boardroom boardroom) {
		for (Reservation completedBookingRequest : completedBookingRequests) {
			if (completedBookingRequest.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(Reservation completedBookingRequest) {
		completedBookingRequests.remove(completedBookingRequest);

	}

	@Override
	public boolean existWithBooking(AssignedBooking assignedBooking) {
		return completedBookingRequests.contains(assignedBooking);
	}
}
