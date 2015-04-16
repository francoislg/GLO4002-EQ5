package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;

public interface ReservationsRepository {
	public void persist(Reservation assignedBooking);

	public void remove(Reservation assignedBooking);

	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException;

	public Collection<Reservation> retrieveAll();

	public boolean existsWithBoardroom(Boardroom boardroom);
}
