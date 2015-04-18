package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;

	public Boardrooms(BoardroomRepository boardroomsRepo, ReservationAssigner reservationAssigner) {
		this.boardroomsRepository = boardroomsRepo;
	}

	public Collection<Boardroom> getBoardroomWithStrategy(BoardroomsSortingStrategy boardroomsSortingStrategy) {
		return boardroomsSortingStrategy.sort(boardroomsRepository);

	}

}
