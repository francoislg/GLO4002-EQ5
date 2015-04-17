package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;

public class ReservationInMemoryRepository implements ReservationsRepository {

	private List<Reservation> reservations = new ArrayList<Reservation>();

	@Override
	public void persist(Reservation reservation) {
		if (!reservations.contains(reservation)) {
			reservations.add(reservation);
		}
	}

	@Override
	public Collection<Reservation> retrieveAll() {
		return reservations;
	}

	@Override
	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException {
		for (Reservation reservation : reservations) {
			if (reservation.containsBooking(assignedBooking)) {
				return reservation;
			}
		}
		throw new ReservationNotFoundException();
	}

	@Override
	public boolean existsWithBoardroom(Boardroom boardroom) {
		for (Reservation completedBookingRequest : reservations) {
			if (completedBookingRequest.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(Reservation reservation) {
		reservations.remove(reservation);
	}
}
