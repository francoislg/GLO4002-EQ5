package ca.ulaval.glo4002.GRAISSE.boardroom;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.booking.BookingAssignedTrigger;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;
	private ArrayList<BookingAssignedTrigger> triggers;

	public Boardrooms(BoardroomRepository boardroomsRepo) {
		this.boardroomsRepository = boardroomsRepo;
		this.triggers = new ArrayList<BookingAssignedTrigger>();
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsStrategy boardroomsStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsStrategy.sort(boardroomsRepository.retrieveAll());
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign)) {
				boardroomsRepository.persist(boardroom);
				notifyTriggers(bookingToAssign);
				return;
			}
		}
		throw new UnableToAssignBookingException();
	}
	
	
	public void notifyTriggers(BookingAssignable booking){
		for(BookingAssignedTrigger trigger : triggers) {
			trigger.update(booking);
		}
	}
	
	public void registerBookingAssignedTrigger(BookingAssignedTrigger trigger) {
		triggers.add(trigger);
	}
}
