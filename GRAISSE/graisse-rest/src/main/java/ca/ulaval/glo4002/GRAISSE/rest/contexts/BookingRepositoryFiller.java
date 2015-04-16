package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;

public class BookingRepositoryFiller {
	private FillerConfig config;

	public BookingRepositoryFiller(FillerConfig config) {
		this.config = config;
	}

	public void fill(BookingRepository repository) {
		for (Booking booking : config.getBookings()) {
			repository.persist(booking);
		}
	}
}
