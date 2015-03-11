package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.Collection;

public interface BoardroomsRepository {
	public void add(Boardroom boardroom);

	public boolean isEmpty();

	public Boardroom findBoardroomWithName(String boardroomName);

	public void saveBoardroomModification(Boardroom boardroom);

	public Collection<Boardroom> getAllBoardroom();

}
