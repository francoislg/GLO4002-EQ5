package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class BookingInMemoryRepository implements BookingRepository {

	private List<Booking> bookings;

	public BookingInMemoryRepository() {
		bookings = new ArrayList<Booking>();
	}

	@Override
	public void persist(Booking booking) {
		if (isNotAlreadyInMemory(booking)) {
			bookings.add(booking);
		}
	}

	@Override
	public Collection<Booking> retrieveAll() {
		return bookings;
	}

	private boolean isNotAlreadyInMemory(Booking booking) {
		return !bookings.contains(booking);
	}

	@Override
	public Booking retrieveBooking(Email promoter, String name) {
		for(Booking booking : bookings){
			if(booking.hasPromoter(promoter) && booking.hasName(name)){
				return booking;
			}
		}
		throw new CouldNotFindBooking();
	}

}