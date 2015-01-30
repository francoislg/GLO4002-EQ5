package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Observable;

import javax.management.InvalidAttributeValueException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Trigger.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.Trigger.Worker;

@RunWith(MockitoJUnitRunner.class)
public class TimedSequentialTriggerTest {

	private static final int INVALID_NUMBER_OF_MINUTES = 0;
	private static final int VALID_NUMBER_OF_MINUTES = 1;
	private static final boolean HAS_JOB_TO_DO = true;
	private static final boolean OBSERVABLE_IS_THE_WORKER = true;
	
	@Mock
	private Worker mockedWorker;
	
	private TimedSequentialTrigger timedSequentialTrigger;
	private Observable anObservable = new Observable();

	@Before
	public void setUp() {
		timedSequentialTrigger = spy(new TimedSequentialTrigger(
				mockedWorker));
	}

	@Test
	public void newTimedSequentialTriggerIsNotRunning() {
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void newTimedSequentialTriggerThrowExceptionWhenSettingInvalidMinutesInterval()
			throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(INVALID_NUMBER_OF_MINUTES);
	}

	@Test(expected = IllegalStateException.class)
	public void newTimedSequentialTriggerShouldThrowExceptionWHenCallingUnsettedInterval() {
		timedSequentialTrigger.getInterval();
	}

	@Test
	public void newTimedSequentialTriggerShouldReturnSameIntervalThatWasSetBeforeWith()
			throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(VALID_NUMBER_OF_MINUTES);
		int triggerInterval = timedSequentialTrigger.getInterval();
		assertEquals(VALID_NUMBER_OF_MINUTES, triggerInterval);

	}

	@Test
	public void timedSequentialTriggerShouldStartTimerWhenUpdateMethodIsCalledAndNWorkerHasJobToDo()
			throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(VALID_NUMBER_OF_MINUTES);

		when(mockedWorker.hasWorkToDO()).thenReturn(HAS_JOB_TO_DO);
		when(timedSequentialTrigger.observableIsTheWorker(anObservable))
				.thenReturn(OBSERVABLE_IS_THE_WORKER);
		timedSequentialTrigger.update(anObservable, null);

		verify(timedSequentialTrigger, times(1)).startTimer();
	}

	@Test
	public void timedSequentialTriggerShouldBeRunningAfterStartTimerHasBeenCalled() {
		timedSequentialTrigger.startTimer();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertTrue(isRunning);
	}

	@Ignore
	@Test
	public void startedTimedSequentialTriggerShouldNotBeRunningAfterReset() {
		timedSequentialTrigger.startTimer();
		timedSequentialTrigger.reset();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}

}