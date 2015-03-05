package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Observable;

import javax.management.InvalidAttributeValueException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimedSequentialTriggerTest {

	private static final long INVALID_NUMBER_OF_MINUTES = 0;
	private static final long THE_DEFAULT_INTERVAL_IN_MILLISECONDS = 600000;

	private TimedSequentialTrigger timedSequentialTrigger;
	
	@Mock
	private Worker mockedWorker;	

	@Mock
	private Observable anObservable;

	@Mock
	private TriggerTimerTask triggerTimerTask;

	@Mock
	private TriggerTimer triggerTimer;
	
	@Mock
	private TriggerTimerStrategyFactory triggerTimerStrategyFactory;

	@Before
	public void setUp() throws InvalidAttributeValueException {

		ensureThatMockedTimerDoesNotStartANewThread();
		timedSequentialTrigger = new TimedSequentialTrigger(mockedWorker, triggerTimerTask, triggerTimerStrategyFactory);
	}

	private void ensureThatMockedTimerDoesNotStartANewThread() {
		doNothing().when(triggerTimer).schedule(triggerTimerTask, THE_DEFAULT_INTERVAL_IN_MILLISECONDS);
	}

	@Test
	public void newTimedSequentialTriggerIsNotRunning() {
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void newTimedSequentialTriggerThrowExceptionWhenSettingInvalidMinutesInterval() throws InvalidAttributeValueException {
		timedSequentialTrigger = new TimedSequentialTrigger(mockedWorker, triggerTimerTask, INVALID_NUMBER_OF_MINUTES);
	}

	@Test
	public void timedSequentialTriggerShouldBeRunningAfterStartTimerHasBeenCalled() throws InvalidAttributeValueException {
		startMockedTimer();

		boolean isRunning = timedSequentialTrigger.isRunning();
		assertTrue(isRunning);
	}

	@Test
	public void startedTimedSequentialTriggerShouldNotBeRunningAfterReset() throws InvalidAttributeValueException {
		startMockedTimer();

		timedSequentialTrigger.reset();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}

	@Test
	public void whenTriggerTriggTheTargetDoWorkShouldGetCalled() {
		timedSequentialTrigger.setOff();

		verify(mockedWorker).doWork();
	}

	@Test
	public void timedSequentialTriggerShouldCalledScheduleOfTheTimerOnlyOnceWithTheGoodTimerTaskAndDelay() throws InvalidAttributeValueException {
		startMockedTimer();

		verify(triggerTimer, times(1)).schedule(triggerTimerTask, THE_DEFAULT_INTERVAL_IN_MILLISECONDS);
	}

	@Test
	public void timedSequentialTriggerShouldCallTimerCancelMethodWhenReseting() throws InvalidAttributeValueException {
		startMockedTimer();
		timedSequentialTrigger.reset();
		verify(triggerTimer, times(1)).cancel();
	}

	@Test
	public void timedSequentialTriggerShouldCallSetWorkerOfTimerTaskAtInitialisation() {
		verify(triggerTimerTask, times(1)).setWorker(mockedWorker);
	}

	private void startMockedTimer() throws InvalidAttributeValueException {
		doReturn(triggerTimer).when(triggerTimerStrategyFactory).createTimer();
		timedSequentialTrigger.doUpdatedByWorkerWithWorkToDo();
	}

}