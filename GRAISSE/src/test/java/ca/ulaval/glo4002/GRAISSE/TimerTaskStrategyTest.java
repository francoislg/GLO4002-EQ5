package ca.ulaval.glo4002.GRAISSE;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TimerTaskStrategyTest {

	private Worker worker;
	private TimerTaskStrategy timerTaskStrategy;

	@Before
	public void setUp() {
		timerTaskStrategy = new TimerTaskStrategy();
	}

	@Test(expected = IllegalStateException.class)
	public void timerTaskStrategyShouldThrowIllegalStateExceptionIfRunMethodIsCalledAndWorkerIsNull() {
		timerTaskStrategy.run();
	}

	@Test
	public void timerTaskStrategyShouldCalledWorkerDoWorkWhenRunisCalled() {
		worker = Mockito.mock(Worker.class);

		timerTaskStrategy.setWorker(worker);
		timerTaskStrategy.run();

		Mockito.verify(worker, Mockito.times(1)).doWork();
	}

}