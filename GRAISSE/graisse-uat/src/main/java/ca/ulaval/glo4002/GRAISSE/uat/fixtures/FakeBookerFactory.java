package ca.ulaval.glo4002.GRAISSE.uat.fixtures;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.InterfaceReservationBoardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.InterfaceReservationBooking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;

public class FakeBookerFactory {
	
	public static Booker create(){
		return new Booker(getBookings(), getBoardrooms(), getReservationBooking());
	}
	
	private static Bookings getBookings(){
		return new Bookings(ServiceLocator.getInstance().resolve(BookingRepository.class),
				getReservationBooking());
	}
	
	private static Boardrooms getBoardrooms(){
		return new Boardrooms(ServiceLocator.getInstance().resolve(BoardroomRepository.class),
				getReservationBoardroom());
	}
	
	private static InterfaceReservationBoardroom getReservationBoardroom(){
		return (InterfaceReservationBoardroom) getReservation();
	}
	
	private static InterfaceReservationBooking getReservationBooking(){
		return (InterfaceReservationBooking) getReservation();
	}
	
	private static Reservation getReservation(){
		/**
		 * to do : implements ReservationRepository
		 */
		return null;
	}
}
