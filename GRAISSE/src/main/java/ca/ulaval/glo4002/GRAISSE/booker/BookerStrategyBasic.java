package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategy;

public class BookerStrategyBasic implements BookerStrategy {

	private BookingsStrategy bookingsStrategy;
	private BoardroomsSortingStrategy boardroomsSortingStrategy;

	public BookerStrategyBasic(BookingsStrategy bookingsStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		this.bookingsStrategy = bookingsStrategy;
		this.boardroomsSortingStrategy = boardroomsSortingStrategy;
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsSortingStrategy);
	}
}