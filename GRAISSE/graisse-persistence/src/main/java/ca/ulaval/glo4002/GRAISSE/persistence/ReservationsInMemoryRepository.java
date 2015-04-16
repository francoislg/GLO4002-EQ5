package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class ReservationsInMemoryRepository implements ReservationsRepository {

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
		for (Reservation reservation : reservations) {
			if (reservation.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void remove(Reservation completedBookingRequest) {
		reservations.remove(completedBookingRequest);
	}

	@Override
	public boolean hasReservation(Email promoter, String boardroomName) {
		for (Reservation reservation : reservations) {
			if (reservation.hasPromoter(promoter) && reservation.hasBoardroomName(boardroomName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Reservation retrieve(Email promoter, String boardroomName)
			throws ReservationNotFoundException {
		for (Reservation reservation : reservations) {
			if (reservation.hasPromoter(promoter) && reservation.hasBoardroomName(boardroomName)) {
				return reservation;
			}
		}
		throw new ReservationNotFoundException();
	}
}