package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingRepositoryFiller {
	public void fill(BookingRepository repository) {
		User mainUser = new User(new Email("USER@gmail.com"));
        repository.persist(new Booking(mainUser, "VCH-3850", 50));
        repository.persist(new Booking(mainUser, "PLT-2550", 100));
    }
}
