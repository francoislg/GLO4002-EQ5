package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategy;

public class BookerStrategyBasic implements BookerStrategy {

	private BookingsStrategy bookingsStrategy;
	private BoardroomsStrategy boardroomsStrategy;

	public BookerStrategyBasic(BookingsStrategy bookingsStrategy, BoardroomsStrategy boardroomsStrategy) {
		this.bookingsStrategy = bookingsStrategy;
		this.boardroomsStrategy = boardroomsStrategy;
	}

	public void assignBookings(Boardrooms boardrooms, Bookings bookings) {
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
	}
}