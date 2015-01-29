package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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

	public boolean assignBookingToMinSeatsBoardroom(Booking bookingToAssign) {
		Stream<Boardroom> boardroomsStream = getStreamSortByNumberOfSeats();
		Iterator<Boardroom> boardroomIt = boardroomsStream.iterator();
		while (boardroomIt.hasNext()) {
			Boardroom boardroom = boardroomIt.next();
			if (boardroom.assign(bookingToAssign)) {
				return true;
			}
		}
		return false;
	}

	private Stream<Boardroom> getStreamSortByNumberOfSeats() {
		Comparator<Boardroom> byNumberOfSeats = (e1, e2) -> Integer.compare(e1.getNumberOfSeats(), e2.getNumberOfSeats());
		return boardrooms.stream().sorted(byNumberOfSeats);
	}
}
