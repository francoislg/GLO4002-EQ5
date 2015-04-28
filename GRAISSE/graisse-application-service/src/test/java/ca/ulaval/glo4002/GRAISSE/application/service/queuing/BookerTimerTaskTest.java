package ca.ulaval.glo4002.GRAISSE.application.service.queuing;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;

@RunWith(MockitoJUnitRunner.class)
public class BookerTimerTaskTest {

	@Mock
	Booker booker;

	BookerTimerTask timerTaskStrategy;

	@Test
	public void whenRunningTimerTaskShouldCallBookerAssignBookings() {
		timerTaskStrategy = new BookerTimerTask(booker);

		timerTaskStrategy.run();

		verify(booker).assignBookings();
	}
}