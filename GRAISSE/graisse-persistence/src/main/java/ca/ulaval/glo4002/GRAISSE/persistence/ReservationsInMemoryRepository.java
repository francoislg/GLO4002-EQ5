package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;

public class ReservationsInMemoryRepository implements ReservationsRepository {

	private List<Reservation> completedBookingRequests = new ArrayList<Reservation>();

	@Override
	public void persist(Reservation completedBookingRequest) {
		if (!completedBookingRequests.contains(completedBookingRequest)) {
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
}
