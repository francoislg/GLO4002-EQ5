package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.List;

public class Boardrooms {
	private List<Boardroom> boardrooms = new ArrayList<Boardroom>();

	public Boardrooms() {

	}

	public void addBoardroom(Boardroom boardroom) {
		boardrooms.add(boardroom);
	}

	public boolean isEmpty() {
		return boardrooms.isEmpty();
	}

	public Boardroom findBoardroomWithName(String boardroomName) throws BoardroomNotFoundExeption {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.isMyName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundExeption();
	}

	public boolean assignBookingToBoardroom(Booking bookingToAssign) {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.assign(bookingToAssign)) {
				return true;
			}
		}
		return false;
	}

	public boolean assignToMaxSeatsBoardroom(Booking bookingToAssign) {
		return false;
	}
}
