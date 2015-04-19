package ca.ulaval.glo4002.GRAISSE.uat.fixtures;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.shared.ServiceLocator;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingCanceller;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservations;

public class FakeBookerFactory {
	
	public static Booker create() {
		return new Booker(getBookings(), getBoardrooms(), getReservations());
	}
	
	private static Bookings getBookings() {
		return new Bookings(ServiceLocator.getInstance().resolve(BookingRepository.class),
				getBookingCanceller());
	}
	
	private static BookingCanceller getBookingCanceller() {
		return null; //NEED BIG REFACTOR!! 
	}
	
	private static Boardrooms getBoardrooms() {
		return new Boardrooms(ServiceLocator.getInstance().resolve(BoardroomRepository.class));
	}
	
	private static Reservations getReservations() {
		return new Reservations(ServiceLocator.getInstance().resolve(ReservationRepository.class),
				getBoardrooms(), getBookings());
	}
}
