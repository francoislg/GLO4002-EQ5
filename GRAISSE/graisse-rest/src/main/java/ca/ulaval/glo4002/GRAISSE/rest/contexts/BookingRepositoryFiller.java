package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingRepositoryFiller {
	private FillerConfig config;

	public BookingRepositoryFiller(FillerConfig config) {
		this.config = config;
	}

	public void fill(BookingRepository repository) {
		User mainUser = config.getMainUser();
		repository.persist(new Booking(mainUser, 50));
		repository.persist(new Booking(mainUser, 100));
		repository.persist(new Booking(mainUser, 20));
	}
}
