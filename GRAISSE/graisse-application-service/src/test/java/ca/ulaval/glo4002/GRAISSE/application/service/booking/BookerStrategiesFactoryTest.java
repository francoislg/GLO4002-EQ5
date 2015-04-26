package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategiesFactoryTest {

	@Mock
	Boardrooms boardrooms;

	BookerStrategiesFactory bookingStrategiesFactory;

	@Before
	public void setUp() {
		bookingStrategiesFactory = new BookerStrategiesFactory();
	}

	@Test
	public void createBasicStrategyShouldReturnBookingStrategyBasic() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.create(BookerStrategiesFactory.StrategyType.BASIC);
		assertEquals(BookerStrategyDefault.class, bookingStrategy.getClass());
	}

	@Test
	public void createMaximiseStrategyShouldReturnBookingStrategyOptimize() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.create(BookerStrategiesFactory.StrategyType.BYSEATS);
		assertEquals(BookerStrategyDefault.class, bookingStrategy.getClass());
	}

	@Test
	public void createPriorityStrategyShouldReturnBookingStrategyPriority() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.create(BookerStrategiesFactory.StrategyType.PRIORITY);
		assertEquals(BookerStrategyDefault.class, bookingStrategy.getClass());
	}
}
