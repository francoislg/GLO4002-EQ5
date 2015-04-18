package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;

	public Boardrooms(BoardroomRepository boardroomsRepo) {
		this.boardroomsRepository = boardroomsRepo;
	}

	public Collection<Boardroom> getBoardroomsWithStrategy(BoardroomsSortingStrategy boardroomsSortingStrategy) {
		return boardroomsSortingStrategy.sort(boardroomsRepository);

	}

}
