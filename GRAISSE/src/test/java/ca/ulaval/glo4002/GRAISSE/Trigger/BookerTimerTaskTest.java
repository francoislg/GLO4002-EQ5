package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

@RunWith(MockitoJUnitRunner.class)
public class BookerTimerTaskTest {

	@Mock
	private Booker booker;
	private BookerTimerTask timerTaskStrategy;

	@Test
	public void timerTaskStrategyShouldCalledWorkerDoWorkWhenRunisCalled() {
		timerTaskStrategy = new BookerTimerTask(booker);
		
		timerTaskStrategy.run();

		verify(booker).assignBookings();
	}
}
