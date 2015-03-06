package ca.ulaval.glo4002.GRAISSE.Boardroom;

import java.util.ArrayList;
import java.util.Collection;

public class Boardrooms {
	private Collection<Boardroom> boardrooms = new ArrayList<Boardroom>();

	public Boardrooms() {

	}

	public void addBoardroom(Boardroom boardroom) {
		boardrooms.add(boardroom);
	}

	public boolean isEmpty() {
		return boardrooms.isEmpty();
	}

	public Boardroom findBoardroomWithName(String boardroomName) throws BoardroomNotFoundException {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.isMyName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundException();
	}

	public boolean assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.sort(boardrooms);
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				return true;
			}
		}
		return false;
	}
}
