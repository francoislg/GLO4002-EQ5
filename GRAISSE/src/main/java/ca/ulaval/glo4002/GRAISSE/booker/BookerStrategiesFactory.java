package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyBySeats;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategyByPriority;

public class BookerStrategiesFactory {

	public enum StrategyType {
		BASIC, OPTIMIZE, PRIORITY
	}

	public BookerStrategy create(StrategyType strategyType) {
		BookerStrategy bookerStrategy;
		
		switch (strategyType) {
			case OPTIMIZE:
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
