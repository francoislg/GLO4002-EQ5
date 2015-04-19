package ca.ulaval.glo4002.GRAISSE.core.reservation;

import java.util.Collection;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface ReservationRepository {

	public void persist(Reservation reservation);

	public void remove(Reservation reservation);

	public boolean exists(AssignedBooking assignedBooking);

	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException;

	public Collection<Reservation> retrieveAll();

	public boolean exists(Boardroom boardroom);

	public boolean exists(Email email, BookingID bookingID);

	public Reservation retrieve(Email email, BookingID bookingID) throws ReservationNotFoundException;

}
