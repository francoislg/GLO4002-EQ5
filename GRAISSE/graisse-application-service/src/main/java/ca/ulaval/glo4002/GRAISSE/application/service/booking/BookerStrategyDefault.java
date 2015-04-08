package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;

public class BookerStrategyDefault implements BookerStrategy {

	private BookingsSortingStrategy bookingsSortingStrategy;
	private BoardroomsSortingStrategy boardroomsSortingStrategy;

	public BookerStrategyDefault(BookingsSortingStrategy bookingsSortingStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		this.bookingsSortingStrategy = bookingsSortingStrategy;
		this.boardroomsSortingStrategy = boardroomsSortingStrategy;
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsSortingStrategy, boardroomsSortingStrategy);
	}
}