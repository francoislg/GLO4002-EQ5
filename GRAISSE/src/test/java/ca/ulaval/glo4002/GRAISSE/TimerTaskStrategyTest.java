package ca.ulaval.glo4002.GRAISSE;

import org.junit.Test;
import org.mockito.Mockito;

public class TimerTaskStrategyTest {

	private Worker worker;

	@Test
	public void timerTaskStrategyShouldCalledWorkerDoWorkWhenRunisCalled() {
		worker = Mockito.mock(Worker.class);

		TimerTaskStrategy timerTaskStrategy = new TimerTaskStrategy(worker);
		timerTaskStrategy.run();

		Mockito.verify(worker, Mockito.times(1)).doWork();
	}

}
