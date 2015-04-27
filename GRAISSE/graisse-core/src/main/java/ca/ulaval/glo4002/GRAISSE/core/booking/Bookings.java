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

	public List<BookingDTO> getUnassignedBookingsWithEmail(Email email) {
		Collection<Booking> retrievedBookings = bookingRepository.retrieveAllForEmail(email);
		List<BookingDTO> bookingDTOList = new ArrayList<BookingDTO>();

		for (Booking booking : retrievedBookings) {
			if (!booking.isAssigned()) {
				bookingDTOList.add(convertToDTO(booking));
			}
		}
		return bookingDTOList;
	}

	public BookingDTO retrieve(Email email, BookingID bookingID) {
		Booking retrievedBooking = bookingRepository.retrieve(email, bookingID);
		return convertToDTO(retrievedBooking);
	}

	public boolean hasBooking(Email email, BookingID bookingID) {
		return bookingRepository.exists(email, bookingID);
	}

	private BookingDTO convertToDTO(Booking booking) {
		return new BookingDTO(booking.getID(), booking.getNumberOfSeats(), booking.getPromoterEmail().getValue(), booking.getState(), "");
	}
}