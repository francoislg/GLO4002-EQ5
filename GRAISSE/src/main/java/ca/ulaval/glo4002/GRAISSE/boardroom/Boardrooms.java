package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.Persistence.BoardroomsRepository;

public class Boardrooms {

	private RepoBoardroom boardroomsRepo;

	public Boardrooms() {
		boardroomsRepo = new BoardroomsRepository();
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.sort(boardroomsRepo.getAllBoardroom());
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				boardroomsRepo.saveBoardroomModification(boardroom);
				return;
			}
		}
		throw new UnableToAssignBookingException();
	}
}
