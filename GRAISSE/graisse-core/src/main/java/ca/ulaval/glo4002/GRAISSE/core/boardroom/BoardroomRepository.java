package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;

public interface BoardroomRepository {

	public void persist(Boardroom boardroom);

	public Boardroom retrieve(String boardroomName);

	public Collection<Boardroom> retrieveAll();
}