package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public class BoardroomsSortingStrategyDefault implements BoardroomsSortingStrategy {

	public Collection<Boardroom> sort(BoardroomRepository boardroomRepo) {
		return boardroomRepo.retrieveAll();
	}
}
