package ca.ulaval.glo4002.GRAISSE.Booking;

import java.util.Collection;

public class BookingsStrategyBasic implements BookingsStrategy {

	public BookingsStrategyBasic() {

	}

	public Collection<Booking> format(Collection<Booking> bookings) {
		return bookings;
	}

}