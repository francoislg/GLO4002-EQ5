package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public interface BoardroomRepository {

	public void persist(Boardroom boardroom);

	public Boardroom retrieve(String boardroomName);

	public Collection<Boardroom> retrieveAll();
	
	public Collection<Boardroom>  retrieveBoardroomsOrderedByNumberOfSeats();
}