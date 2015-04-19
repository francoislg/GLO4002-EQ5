package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Bookings {

	private BookingRepository bookingRepository;

	public Bookings(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
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

	public Collection<Booking> getBookingsWithStrategy(BookingsSortingStrategy bookingsSortingStrategy) {
		return bookingsSortingStrategy.sort(bookingRepository);
	}

	public void assignBooking(Booking booking) {
		booking.assign();
		bookingRepository.persist(booking);
	}

	public List<BookingDTO> getBookingsWithEmail(Email email) {
		Collection<Booking> retrievedBookings = bookingRepository.retrieveAllForEmail(email);
		List<BookingDTO> bookingDTOList = new ArrayList<BookingDTO>();

		for (Booking booking : retrievedBookings) {
			bookingDTOList.add(convertToDTO(booking));
		}
		return bookingDTOList;
	}

	private BookingDTO convertToDTO(Booking booking) {
		return new BookingDTO(booking.getID(), booking.getNumberOfSeats(), booking.getPromoterEmail(), booking.getState(), "");
	}

}