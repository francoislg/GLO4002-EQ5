package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;

public class BookingsStrategyBasic extends BookingsStrategy {

	public BookingsStrategyBasic() {

	}

	@Override
	public Collection<Booking> format(Collection<Booking> bookings) {
		return bookings;
	}

}
