package ca.ulaval.glo4002.GRAISSE.application.service.canceling;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;

public class Canceler {

	private BookingRepository bookingRepository;
	private ReservationRepository reservationRepository;

	private List<Notifyer<AssignedBooking>> notifiers;

	public Canceler(BookingRepository bookingRepository, ReservationRepository reservationRepository) {
		this.bookingRepository = bookingRepository;
		this.reservationRepository = reservationRepository;
		notifiers = new ArrayList<Notifyer<AssignedBooking>>();
	}

	public void cancel(Booking booking) {
		cancelBooking(booking.getPromoterEmail(), booking.getID());
		cancelReservation(booking.getPromoterEmail(), booking.getID());
		notifyCancelling(booking);
	}

	public void registerObserver(Notifyer<AssignedBooking> observer) {
		notifiers.add(observer);
	}

	private void notifyCancelling(AssignedBooking booking) {
		for (Notifyer<AssignedBooking> notifyer : notifiers) {
			notifyer.notify(booking);
		}
	}

	private void cancelBooking(Email email, BookingID bookingID) {
		if (bookingRepository.exists(email, bookingID)) {
			Booking bookingToCancel = bookingRepository.retrieve(email, bookingID);
			bookingToCancel.cancel();
			bookingRepository.persist(bookingToCancel);
		}
	}

	private void cancelReservation(Email email, BookingID bookingID) {
		if (reservationRepository.exists(email, bookingID)) {
			Reservation reservationToCancel = reservationRepository.retrieve(email, bookingID);
			reservationToCancel.cancel();
			reservationRepository.remove(reservationToCancel);
		}
	}
}
