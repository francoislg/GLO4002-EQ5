package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategy;

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