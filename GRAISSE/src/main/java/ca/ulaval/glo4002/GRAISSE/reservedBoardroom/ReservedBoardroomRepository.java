package ca.ulaval.glo4002.GRAISSE.reservedBoardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.booking.AssignedBooking;

public interface ReservedBoardroomRepository {
	public void persist(ReservedBoardroom assignedBooking);

	public void remove(ReservedBoardroom assignedBooking);

	public ReservedBoardroom retrieve(AssignedBooking assignedBooking) throws ReservedBoadroomNotFoundException;

	public Collection<ReservedBoardroom> retrieveAll();

	public boolean existsWithBoardroom(Boardroom boardroom);
}
