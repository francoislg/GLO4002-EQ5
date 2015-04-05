package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;
	private InterfaceReservationBoardroom interfaceReservationBoardroom;
	private ArrayList<Notifyer<BookingAssignable>> notifyers;

	public Boardrooms(BoardroomRepository boardroomsRepo, InterfaceReservationBoardroom interfaceReservationBoardroom) {
		this.boardroomsRepository = boardroomsRepo;
		this.notifyers = new ArrayList<Notifyer<BookingAssignable>>();
		this.interfaceReservationBoardroom = interfaceReservationBoardroom;
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsSortingStrategy.sort(boardroomsRepository.retrieveAll());
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.assign(bookingToAssign, interfaceReservationBoardroom)) {
				boardroomsRepository.persist(boardroom);
				notifyTriggers(bookingToAssign);
				return;
			}
		}
		notifyTriggers(bookingToAssign);
		throw new UnableToAssignBookingException();
	}

	private void notifyTriggers(BookingAssignable booking) {
		for (Notifyer<BookingAssignable> notifyer : notifyers) {
			notifyer.notify(booking);
		}
	}

	public void registerObserver(Notifyer<BookingAssignable> observer) {
		notifyers.add(observer);
	}
}
