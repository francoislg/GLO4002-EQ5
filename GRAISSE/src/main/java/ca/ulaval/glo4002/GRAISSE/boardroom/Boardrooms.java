package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.UnableToAssignBookingException;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;
	private ArrayList<BookingTrigger> triggers;

	public Boardrooms(BoardroomRepository boardroomsRepo) {
		this.boardroomsRepository = boardroomsRepo;
		this.triggers = new ArrayList<BookingTrigger>();
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsSortingStrategy.sort(boardroomsRepository.retrieveAll());
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				boardroomsRepository.persist(boardroom);
				notifyTriggers(bookingToAssign);
				return;
			}
		}
		throw new UnableToAssignBookingException();
	}
	
	private void notifyTriggers(BookingAssignable booking){
		for(BookingTrigger trigger : triggers) {
			trigger.update(booking);
		}
	}
	
	public void registerBookingTrigger(BookingTrigger trigger) {
		triggers.add(trigger);
	}
}
