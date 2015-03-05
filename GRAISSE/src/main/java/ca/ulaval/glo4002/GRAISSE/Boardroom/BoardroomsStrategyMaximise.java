package ca.ulaval.glo4002.GRAISSE.Boardroom;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BoardroomsStrategyMaximise implements BoardroomsStrategy {

	public Collection<Boardroom> format(Collection<Boardroom> boardrooms) {
		Comparator<Boardroom> byNumberOfSeats = (boardroom1, boardroom2) -> boardroom1.compareNumberOfSeatsToBoardroomNumberOfSeats(boardroom2);
		return boardrooms.stream().sorted(byNumberOfSeats).collect(Collectors.toList());
	}
}
