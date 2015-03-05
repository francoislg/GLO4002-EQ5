package ca.ulaval.glo4002.GRAISSE.Booker;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;

public interface BookerStrategy {

	public void assignBookings(Boardrooms boardrooms, Bookings bookings);

}
