package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.Collection;
import java.util.Iterator;

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
		return getNumberOfUnassignedBookings() > 0;
	}

	public int getNumberOfUnassignedBookings() {
		return getAssignableBookings().size();
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

	private Collection<Booking> getAssignableBookings() {
		Collection<Booking> bookings = bookingRepository.retrieveAll();
		for (Iterator<Booking> bookingIter = bookings.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (!booking.isAssignable()) {
				bookingIter.remove();
			}
		}
		return bookings;
	}
}