package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public class BoardroomsSortingStrategyBySeats implements BoardroomsSortingStrategy {

	public Collection<Boardroom> sort(BoardroomRepository boardroomRepo) {
		Collection<Boardroom> boardrooms = boardroomRepo.retrieveBoardroomsOrderedByNumberOfSeats();
		return boardrooms;
	}
}
