package ca.ulaval.glo4002.GRAISSE;

public class BookingStrategiesFactory {

	public BookingStrategy createBasicStrategy(Boardrooms boardrooms) {
		return new BookingStrategyBasic(boardrooms);
	}

}
