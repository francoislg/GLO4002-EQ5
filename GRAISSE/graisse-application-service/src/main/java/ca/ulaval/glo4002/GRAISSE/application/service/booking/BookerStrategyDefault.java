package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservations;

public class BookerStrategyDefault implements BookerStrategy {

	private BookingsSortingStrategy bookingsSortingStrategy;
	private BoardroomsSortingStrategy boardroomsSortingStrategy;

	public BookerStrategyDefault(BookingsSortingStrategy bookingsSortingStrategy, BoardroomsSortingStrategy boardroomsSortingStrategy) {
		this.bookingsSortingStrategy = bookingsSortingStrategy;
		this.boardroomsSortingStrategy = boardroomsSortingStrategy;
	}

	public void assignBookings(Reservations reservations) {
		reservations.assignBookingsToBoardrooms(bookingsSortingStrategy, boardroomsSortingStrategy);
	}
}