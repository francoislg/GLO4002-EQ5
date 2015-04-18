package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class FillerConfig {
	private User mainUser;
	private List<Booking> bookings;
	private List<Boardroom> boardrooms;
	private List<Reservation> reservations;

	public FillerConfig(User mainUser, List<Booking> bookings, List<Boardroom> boardrooms) {
		this.mainUser = mainUser;
		this.bookings = bookings;
		this.boardrooms = boardrooms;
		this.reservations = new ArrayList<Reservation>();
	}

	public User getMainUser() {
		return mainUser;
	}

	public void addReservation(Booking booking, Boardroom boardroom) {
		if (bookings.contains(booking) && boardrooms.contains(boardroom)) {
			reservations.add(new Reservation(boardroom, booking));
		} else {
			throw new CannotAddReservation();
		}
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public List<Boardroom> getBoardrooms() {
		return boardrooms;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}
}
