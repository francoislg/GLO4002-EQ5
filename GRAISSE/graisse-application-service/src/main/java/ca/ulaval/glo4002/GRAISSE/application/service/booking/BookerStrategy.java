package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;

public interface BookerStrategy {

	public void assignBookings(Boardrooms boardrooms, Bookings bookings);
}
