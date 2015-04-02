package ca.ulaval.glo4002.GRAISSE.reservedBoardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public interface ReservationRepository {
	public void persist(Reservation assignedBooking);

	public void remove(Reservation assignedBooking);

	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException;

	public Collection<Reservation> retrieveAll();

	public boolean existsWithBoardroom(Boardroom boardroom);

	public boolean existWithBooking(AssignedBooking assignedBooking);
}
