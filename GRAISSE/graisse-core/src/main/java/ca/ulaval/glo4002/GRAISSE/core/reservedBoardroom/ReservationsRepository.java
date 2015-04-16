package ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface ReservationsRepository {
	public void persist(Reservation assignedBooking);

	public void remove(Reservation assignedBooking);

	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException;

	public Collection<Reservation> retrieveAll();

	public boolean existsWithBoardroom(Boardroom boardroom);

	public boolean hasReservation(Email email, String boardroomName);

	public Reservation retrieve(Email email, String boardroomName) throws ReservationNotFoundException;
}
