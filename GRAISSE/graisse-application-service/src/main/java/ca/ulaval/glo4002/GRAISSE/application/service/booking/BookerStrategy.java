package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;

public interface BookerStrategy {

	public void assignBookings(Reservations reservations);
}
