package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.UnableToAssignBookingException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

public class Boardrooms {

	private BoardroomRepository boardroomsRepository;
	private ReservationAssigner reservationAssigner;
	private List<Notifyer<BookingAssignable>> notifiers;

	public Boardrooms(BoardroomRepository boardroomsRepo, ReservationAssigner reservationAssigner) {
		this.boardroomsRepository = boardroomsRepo;
		this.notifiers = new ArrayList<Notifyer<BookingAssignable>>();
		this.reservationAssigner = reservationAssigner;
	}

	public void assignBookingToBoardroom(BookingAssignable bookingToAssign, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Boardroom> formatedBoardroomList = boardroomsSortingStrategy.sort(boardroomsRepository);
		for (Boardroom boardroom : formatedBoardroomList) {
			if (boardroom.canAssign(bookingToAssign, reservationAssigner)) {
				boardroom.assign(bookingToAssign, reservationAssigner);
				boardroomsRepository.persist(boardroom);
				notifyTriggers(bookingToAssign);
				return;
			}
		}
		notifyTriggers(bookingToAssign);
		throw new UnableToAssignBookingException();
	}

	private void notifyTriggers(BookingAssignable booking) {
		for (Notifyer<BookingAssignable> notifyer : notifiers) {
			notifyer.notify(booking);
		}
	}

	public void registerObserver(Notifyer<BookingAssignable> observer) {
		notifiers.add(observer);
	}
}
