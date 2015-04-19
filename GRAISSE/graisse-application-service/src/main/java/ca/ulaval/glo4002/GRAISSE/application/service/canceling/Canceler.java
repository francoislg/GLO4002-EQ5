package ca.ulaval.glo4002.GRAISSE.application.service.canceling;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class Canceler {
	
	private BookingRepository bookingRepository;
	private ReservationRepository reservationRepository;
	
	public Canceler(BookingRepository bookingRepository, ReservationRepository reservationRepository) {
		this.bookingRepository = bookingRepository;
		this.reservationRepository = reservationRepository;
	}
	
	public void cancel(Email email, BookingID bookingID) {
		cancelBooking(email, bookingID);
		cancelReservation(email, bookingID);
	}
	
	private void cancelBooking(Email email, BookingID bookingID) {
		if(bookingRepository.exists(email, bookingID)) {
			Booking bookingToCancel = bookingRepository.retrieve(email, bookingID);
			bookingToCancel.cancel();
			bookingRepository.persist(bookingToCancel);
		}
	}
	
	private void cancelReservation(Email email, BookingID bookingID) {
		if(reservationRepository.exists(email, bookingID)) {
			Reservation reservationToCancel = reservationRepository.retrieve(email, bookingID);
			reservationToCancel.cancel();
			reservationRepository.remove(reservationToCancel);
		}
	}
}
