package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroomExceptions.BoardroomNotFoundException;
import ca.ulaval.glo4002.GRAISSE.boardroomExceptions.UnableToAssignBookingException;

public class Boardrooms {
	
	private Collection<Boardroom> boardrooms = new ArrayList<Boardroom>();

	public void add(Boardroom boardroom) {
		boardrooms.add(boardroom);
	}

	public boolean isEmpty() {
		return boardrooms.isEmpty();
	}

	public Boardroom findBoardroomWithName(String boardroomName) {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.hasName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundException();
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.sort(boardrooms);
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				return;
			}
		}
		throw new UnableToAssignBookingException();
	}
}
