package ca.ulaval.glo4002.GRAISSE.Booker;

import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;

public interface BookerStrategy {

	public void assignBookings(Boardrooms boardrooms, Bookings bookings);
}
