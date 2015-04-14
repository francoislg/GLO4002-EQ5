package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public interface BoardroomsSortingStrategy {
	
	public Collection<Boardroom> sort(BoardroomRepository boardroomRepo);	
}
