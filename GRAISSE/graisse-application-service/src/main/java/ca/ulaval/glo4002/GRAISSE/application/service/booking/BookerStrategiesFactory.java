package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategyBySeats;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategyByPriority;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingsSortingStrategyDefault;

public class BookerStrategiesFactory {

	public enum StrategyType {
		BASIC, BYSEATS, PRIORITY
	}

	public BookerStrategy create(StrategyType strategyType) {
		BookerStrategy bookerStrategy;

		switch (strategyType) {
		case BYSEATS:
			bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyBySeats());
			break;
		case PRIORITY:
			bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyByPriority(), new BoardroomsSortingStrategyBySeats());
			break;
		case BASIC:
		default:
			bookerStrategy = new BookerStrategyDefault(new BookingsSortingStrategyDefault(), new BoardroomsSortingStrategyDefault());
			break;
		}

		return bookerStrategy;
	}
}
