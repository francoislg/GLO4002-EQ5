package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingCanceller;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Observer;

public class Booker {

	private Bookings bookings;
	private Boardrooms boardrooms;
	private BookerStrategy bookerStrategy;
	private BookingCanceller bookingCanceller;
	private List<Observer<Booker>> observers;

	public Booker(Bookings bookings, Boardrooms boardrooms, BookingCanceller bookingCanceller) {
		this.bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyDefault());
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		this.bookingCanceller = bookingCanceller;
		observers = new ArrayList<Observer<Booker>>();
	}

	public void assignBookings() {
		bookerStrategy.assignBookings(boardrooms, bookings);
		notifyTriggers();
	}

	public void addBooking(Booking bookingToAdd) {
		bookings.add(bookingToAdd);
		notifyTriggers();
	}

	public void cancelBooking(Booking booking) {
		bookingCanceller.cancelBooking(booking);
	}

	public void setBookerStrategy(BookerStrategy bookerStrategy) {
		this.bookerStrategy = bookerStrategy;
	}

	public boolean hasBookingsToAssign() {
		return bookings.hasAssignableBookings();
	}

	public int numberOfBookingsToAssign() {
		return bookings.getNumberOfAssignableBookings();
	}

	private void notifyTriggers() {
		for (Observer<Booker> observer : observers) {
			observer.update(this);
		}
	}

	public void registerTrigger(Observer<Booker> observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public BookingDTO retrieveReservation(Email email, String ID) {
		if(bookingCanceller.hasReservation(email, ID)){
			return bookingCanceller.retrieveReservation(email, ID);
		}
		throw new ReservationNotFoundException();
	}

	public List<BookingDTO> retrieveReservationsForEmail(Email email) {
		return bookings.getBookingsForEmail(email);
	}
}
