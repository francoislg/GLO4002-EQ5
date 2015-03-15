package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.Collection;
import java.util.Iterator;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;

public class Bookings {
	
	private BookingRepository bookingRepository;

	public Bookings(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public void add(Booking booking) {
		bookingRepository.persist(booking);
	}

	public boolean hasUnassignedBookings() {
		return getNumberOfUnassignedBookings() > 0;
	}
	
	public int getNumberOfUnassignedBookings() {
		return getUnassignedBookings().size();
	}
	
	private Collection<Booking> getUnassignedBookings() {
		Collection<Booking> bookings = bookingRepository.retrieveAll();
		for (Iterator<Booking> bookingIter = bookings.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if(booking.isAssigned()) {
				bookingIter.remove();
			}
		}
		return bookings;
	}

	public void assignBookingsToBoardrooms(Boardrooms boardrooms, BookingsStrategy bookingsStrategy, BoardroomsStrategy boardroomsStrategy) {
		Collection<Booking> formatedBookingList = bookingsStrategy.sort(getUnassignedBookings());
		for (Iterator<Booking> bookingIter = formatedBookingList.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy);
			bookingRepository.persist(booking);
			bookingIter.remove();
		}
	}
}
