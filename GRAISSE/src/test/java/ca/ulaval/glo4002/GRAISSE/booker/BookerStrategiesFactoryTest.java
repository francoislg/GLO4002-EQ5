package ca.ulaval.glo4002.GRAISSE.booker;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.booker.BookerStrategiesFactory;
import ca.ulaval.glo4002.GRAISSE.booker.BookerStrategy;
import ca.ulaval.glo4002.GRAISSE.booker.BookerStrategyDefault;

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
		BookerStrategy bookingStrategy = bookingStrategiesFactory.create(BookerStrategiesFactory.StrategyType.OPTIMIZE);
		assertEquals(BookerStrategyDefault.class, bookingStrategy.getClass());
	}

	@Test
	public void createPriorityStrategyShouldReturnBookingStrategyPriority() {
		BookerStrategy bookingStrategy = bookingStrategiesFactory.create(BookerStrategiesFactory.StrategyType.PRIORITY);
		assertEquals(BookerStrategyDefault.class, bookingStrategy.getClass());
	}
}
