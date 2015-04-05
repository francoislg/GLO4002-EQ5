package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BoardroomsSortingStrategyBySeats implements BoardroomsSortingStrategy {

	public Collection<Boardroom> sort(Collection<Boardroom> boardrooms) {
		Comparator<Boardroom> byNumberOfSeats = (boardroom1, boardroom2) -> boardroom1.compareByNumberOfSeats(boardroom2);
		return boardrooms.stream().sorted(byNumberOfSeats).collect(Collectors.toList());
	}
}
