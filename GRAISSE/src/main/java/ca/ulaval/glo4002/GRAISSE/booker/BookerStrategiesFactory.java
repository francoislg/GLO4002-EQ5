package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyDefault;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyBySeats;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategyPriority;

public class BookerStrategiesFactory {

	public enum StrategyType {
		BASIC, OPTIMIZE, PRIORITY
	}

	public BookerStrategy create(StrategyType strategyType) {
		BookerStrategy bookerStrategy;
		
		switch (strategyType) {
			case OPTIMIZE:
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsSortingStrategyBySeats());
				break;
			case PRIORITY:
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyPriority(), new BoardroomsSortingStrategyBySeats());
				break;
			case BASIC:
			default:
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsSortingStrategyDefault());
				break;
		}

		return bookerStrategy;
	}
}
