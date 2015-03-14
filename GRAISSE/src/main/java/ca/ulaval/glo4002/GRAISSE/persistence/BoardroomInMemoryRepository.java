package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.BoardroomNotFoundException;

public class BoardroomInMemoryRepository implements BoardroomRepository {

	private static final String BOARDROOMS_NOT_FOUND = "la salle n'existe pas";
	private Collection<Boardroom> boardrooms = new ArrayList<Boardroom>();

	public void persist(Boardroom boardroom) {
		if(boardroomNotAllreadyInMemory(boardroom)) {
			boardrooms.add(boardroom);
		}
	}
	
	private boolean boardroomNotAllreadyInMemory(Boardroom boardroom) {
		return !boardrooms.contains(boardroom);
	}

	public Boardroom retrieve(String boardroomName) {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.hasName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundException(BOARDROOMS_NOT_FOUND);
	}

	public Collection<Boardroom> retrieveAll() {
		return boardrooms;
	}
}
