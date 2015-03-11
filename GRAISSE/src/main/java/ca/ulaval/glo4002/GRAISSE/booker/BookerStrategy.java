package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;

public interface BookerStrategy {

	public void assignBookings(Boardrooms boardrooms, Bookings bookings);
}
