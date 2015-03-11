package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.UnableToAssignBookingException;

public class Boardrooms {

	private BoardroomsRepository boardroomsRepository;

	public Boardrooms(BoardroomsRepository boardroomsRepo) {
		this.boardroomsRepository = boardroomsRepo;
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.sort(boardroomsRepository.getAllBoardroom());
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				boardroomsRepository.saveBoardroomModification(boardroom);
				return;
			}
		}
		throw new UnableToAssignBookingException();
	}
}
