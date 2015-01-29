package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategiesFactoryTest {

	BookerStrategiesFactory bookingStrategiesFactory;

	@Mock
	private Boardrooms boardrooms;

	@Before
	public void setUp() {
		bookingStrategiesFactory = new BookerStrategiesFactory();
	}

	@Test
	public void createBasicStrategyShouldReturnBookingStrategyBasic() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.createBasicStrategy();
		assertEquals(BookerStrategyBasic.class, bookingStrategy.getClass());
	}

	@Test
	public void createMaximiseStrategyShouldReturnBookingStrategyBasic() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.createMaximiseStrategy();
		assertEquals(BookerStrategyBasic.class, bookingStrategy.getClass());
	}
}
