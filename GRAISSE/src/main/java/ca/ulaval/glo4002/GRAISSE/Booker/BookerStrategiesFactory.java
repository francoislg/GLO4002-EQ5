package ca.ulaval.glo4002.GRAISSE.Booker;

import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategyPriority;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategyOptimize;

public class BookerStrategiesFactory {

	public BookerStrategy createBasicStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyBasic());
	}

	public BookerStrategy createOptimizeStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyOptimize());
	}

	public BookerStrategy createPriorityStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyPriority(), new BoardroomsStrategyOptimize());
	}
}
