package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Trigger.TriggerTimerTaskStrategy;

@RunWith(MockitoJUnitRunner.class)
public class TriggerTimerTaskStrategyTest {

	@Mock
	private Worker worker;
	private TriggerTimerTaskStrategy timerTaskStrategy;

	@Before
	public void setUp() {
		timerTaskStrategy = new TriggerTimerTaskStrategy();
	}

	@Test(expected = IllegalStateException.class)
	public void timerTaskStrategyShouldThrowIllegalStateExceptionIfRunMethodIsCalledAndWorkerIsNull() {
		timerTaskStrategy.run();
	}

	@Test
	public void timerTaskStrategyShouldCalledWorkerDoWorkWhenRunisCalled() {
		timerTaskStrategy.setWorker(worker);
		timerTaskStrategy.run();

		verify(worker, times(1)).doWork();
	}

}
