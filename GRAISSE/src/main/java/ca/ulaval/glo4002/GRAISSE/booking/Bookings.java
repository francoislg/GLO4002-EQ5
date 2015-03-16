package ca.ulaval.glo4002.GRAISSE.booking;

import java.util.Collection;
import java.util.Iterator;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategy;

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

	public void assignBookingsToBoardrooms(Boardrooms boardrooms, BookingsStrategy bookingsStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		Collection<Booking> formatedBookingList = bookingsStrategy.sort(getUnassignedBookings());
		for (Booking booking : formatedBookingList) {
			boardrooms.assignBookingToBoardroom(booking, boardroomsSortingStrategy);
			bookingRepository.persist(booking);
		}
	}
}