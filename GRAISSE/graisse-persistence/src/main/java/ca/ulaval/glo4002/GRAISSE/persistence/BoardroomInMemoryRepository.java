package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.BoardroomNotFoundException;

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
	
	public Collection<Boardroom>  retrieveBoardroomsOrderedByNumberOfSeats(){
		Comparator<Boardroom> byNumberOfSeats = (boardroom1, boardroom2) -> boardroom1.compareByNumberOfSeats(boardroom2);
		return boardrooms.stream().sorted(byNumberOfSeats).collect(Collectors.toList());
	}

}