package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingStrategiesFactoryTest {

	BookingStrategiesFactory bookingStrategiesFactory;

	@Mock
	private Boardrooms boardrooms;

	@Before
	public void setUp() {
		bookingStrategiesFactory = new BookingStrategiesFactory();
	}

	@Test
	public void createBasicStrategyShouldReturnBookingStrategyBasic() {
		BookingStrategy bookingStrategy = bookingStrategiesFactory.createBasicStrategy(boardrooms);
		assertEquals(BookingStrategyBasic.class, bookingStrategy.getClass());
	}

	@Test
	public void createMaximiseStrategyShouldReturnBookingStrategyMaximise() {
		BookingStrategy bookingStrategy = bookingStrategiesFactory.createMaximiseStrategy(boardrooms);
		assertEquals(BookingStrategyMaximise.class, bookingStrategy.getClass());
	}
}
