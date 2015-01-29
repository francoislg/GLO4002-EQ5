package ca.ulaval.glo4002.GRAISSE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;

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

	public Boardroom findBoardroomWithName(String boardroomName) throws BoardroomNotFoundExeption {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.isMyName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundExeption();
	}

	public boolean assignBookingToBoardroom(Booking bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.format(boardrooms);
		for (Boardroom boardroom : formatedBoardroomList) {
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

	public Collection<Boardroom> findAll() {
		return boardrooms;
	}

	private Stream<Boardroom> getStreamSortByNumberOfSeats() {
		Comparator<Boardroom> byNumberOfSeats = (e1, e2) -> Integer.compare(e1.getNumberOfSeats(), e2.getNumberOfSeats());
		return boardrooms.stream().sorted(byNumberOfSeats);
	}
}
