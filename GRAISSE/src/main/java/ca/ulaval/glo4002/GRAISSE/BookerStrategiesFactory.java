package ca.ulaval.glo4002.GRAISSE;

public class BookerStrategiesFactory {

	public BookerStrategy createBasicStrategy() {
		return new BookerStrategyBasic(new BookingsStrategyBasic(), new BoardroomsStrategyBasic());
	}

	public BookerStrategy createMaximiseStrategy() {
		return new BookerStrategyMaximise();
	}
}
