package ca.ulaval.glo4002.GRAISSE.Booker;

import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomsStrategyMaximise;
import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategyBasic;
import ca.ulaval.glo4002.GRAISSE.Booking.BookingsStrategyPriority;

public class BookerStrategiesFactory {

	public BookerStrategy createBasicStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyBasic());
	}

	public BookerStrategy createMaximiseStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyMaximise());
	}

	public BookerStrategy createPriorityStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyPriority(), new BoardroomsStrategyMaximise());
	}
}
