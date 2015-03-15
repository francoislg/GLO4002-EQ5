package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.BookingRepository;

public class BookingInMemoryRepository implements BookingRepository{

	private ArrayList<Booking> bookings;
	
	public BookingInMemoryRepository() {
		bookings = new ArrayList<Booking>();
	}
	
	@Override
	public void persist(Booking booking) {
		if(isNotAllreadyInMemory(booking)) {
			bookings.add(booking);
		}
	}

	@Override
	public Collection<Booking> retrieveAll() {
		return bookings;
	}
	
	private boolean isNotAllreadyInMemory(Booking booking) {
		return !bookings.contains(booking);
	}

}
