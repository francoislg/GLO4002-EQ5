package ca.ulaval.glo4002.GRAISSE;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingStrategiesFactoryTest extends TestCase {

	BookingStrategiesFactory bookingStrategiesFactory;

	@Mock
	private Boardrooms boardrooms;

	@Before
	public void setUp() {
		bookingStrategiesFactory = new BookingStrategiesFactory();
	}

	@Test
	public void setForBasicStrategyShouldReturnBookingStrategyBasic() {
		BookingStrategy bookingStrategy = bookingStrategiesFactory
				.setForBasicStrategy(boardrooms);
		assertEquals(BookingStrategyBasic.class, bookingStrategy.getClass());

	}
}
