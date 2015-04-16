package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;

public class Bookings {

	private BookingRepository bookingRepository;
	private BookingCanceller bookingCanceller;

	public Bookings(BookingRepository bookingRepository, BookingCanceller bookingCanceller) {
		this.bookingRepository = bookingRepository;
		this.bookingCanceller = bookingCanceller;
	}

	public void add(Booking booking) {
		bookingRepository.persist(booking);
	}

	public boolean hasAssignableBookings() {
		return getNumberOfAssignableBookings() > 0;
	}

	public int getNumberOfAssignableBookings() {
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
		bookingCanceller.cancelBooking(booking);
	}
}