package ca.ulaval.glo4002.GRAISSE.booker;

import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategyOptimize;
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
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyOptimize());
				break;
			case PRIORITY:
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyPriority(), new BoardroomsStrategyOptimize());
				break;
			case BASIC:
			default:
				bookerStrategy = new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyBasic());
				break;
		}

		return bookerStrategy;
	}
}
