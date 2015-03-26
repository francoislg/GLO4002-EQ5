package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.BoardroomNotFoundException;

public class BoardroomInMemoryRepository implements BoardroomRepository {

	private List<Boardroom> boardrooms = new ArrayList<Boardroom>();

	private boolean boardroomNotAlreadyInMemory(Boardroom boardroom) {
		return !boardrooms.contains(boardroom);
	}

	@Override
	public void persist(Boardroom boardroom) {
		if (boardroomNotAlreadyInMemory(boardroom)) {
			boardrooms.add(boardroom);
		}
	}

	@Override
	public Boardroom retrieve(String boardroomName) {
		for (Boardroom boardroom : boardrooms) {
			if (boardroom.hasName(boardroomName)) {
				return boardroom;
			}
		}
		throw new BoardroomNotFoundException();
	}

	public Collection<Boardroom> retrieveAll() {
		return boardrooms;
	}
}