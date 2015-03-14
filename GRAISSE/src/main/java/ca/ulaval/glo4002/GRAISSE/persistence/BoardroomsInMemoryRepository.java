package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsRepository;
import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.BoardroomNotFoundException;

public class BoardroomsInMemoryRepository implements BoardroomsRepository {

	private Collection<Boardroom> boardrooms = new ArrayList<Boardroom>();
	private static final String BOARDROOMS_NOT_FOUND = "la salle n'existe pas";

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
		throw new BoardroomNotFoundException(BOARDROOMS_NOT_FOUND);
	}

	public Collection<Boardroom> getAllBoardroom() {

		return boardrooms;
	}

	public void saveBoardroomModification(Boardroom boardroom) {
		removeBoardroom(boardroom.getName());
		boardrooms.add(boardroom);
	}

	private void removeBoardroom(String boardroomName) {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.hasName(boardroomName)) {
				boardrooms.remove(boardroom);
			}
		}
		throw new BoardroomNotFoundException(BOARDROOMS_NOT_FOUND);
	}
}
