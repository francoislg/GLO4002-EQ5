package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class ReservationInMemoryRepository implements ReservationRepository {

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
	public boolean exists(Boardroom boardroom) {
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
	public boolean exists(Email promoter, BookingID bookingID) {
		for (Reservation reservation : reservations) {
			if (reservation.hasPromoter(promoter) && reservation.hasBookingID(bookingID)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Reservation retrieve(Email promoter, BookingID bookingID) throws ReservationNotFoundException {
		for (Reservation reservation : reservations) {
			if (reservation.hasPromoter(promoter) && reservation.hasBookingID(bookingID)) {
				return reservation;
			}
		}
		throw new ReservationNotFoundException();
	}

	@Override
	public boolean exists(AssignedBooking assignedBooking) {
<<<<<<< HEAD
		for (Reservation reservation : reservations) {
			if (reservation.containsBooking(assignedBooking)) {
				return true;
			}
		}
=======
		// TODO Auto-generated method stub
>>>>>>> bb977820b3380ba909a85925a663e6c577bccffd
		return false;
	}
}
