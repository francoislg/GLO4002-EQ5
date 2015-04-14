package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BoardroomsSortingStrategyBySeats implements BoardroomsSortingStrategy {

	public Collection<Boardroom> sort(BoardroomRepository boardroomRepo) {
		Collection<Boardroom> boardrooms = boardroomRepo.retrieveBoardroomsOrderedByNumberOfSeats();
		return boardrooms;
	}
}
