package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;

public class Bookings {

	private BookingRepository bookingRepository;
	private InterfaceReservationBooking interfaceReservationBooking;

	public Bookings(BookingRepository bookingRepository, InterfaceReservationBooking interfaceReservationBooking) {
		this.bookingRepository = bookingRepository;
		this.interfaceReservationBooking = interfaceReservationBooking;
	}

	public void add(Booking booking) {
		bookingRepository.persist(booking);
	}

	public boolean hasAssignableBookings() {
		return getNumberAssignableBookings() > 0;
	}

	public int getNumberAssignableBookings() {
		return bookingRepository.getAssignableBookings().size();
	}

	public void assignBookingsToBoardrooms(Boardrooms boardrooms, BookingsSortingStrategy bookingsSortingStrategy,
			BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Booking> formatedBookingList = bookingsSortingStrategy.sort(bookingRepository);
		for (Booking booking : formatedBookingList) {
			boardrooms.assignBookingToBoardroom(booking, boardroomsSortingStrategy);
			bookingRepository.persist(booking);
		}
	}

	public void cancelBooking(Booking booking) {
		booking.cancel();
		bookingRepository.persist(booking);
		interfaceReservationBooking.cancelBooking(booking);
	}

}