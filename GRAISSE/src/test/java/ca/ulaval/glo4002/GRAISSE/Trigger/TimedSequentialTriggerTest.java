package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
=======
import static org.mockito.Mockito.*;
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java

import java.util.Observable;

import javax.management.InvalidAttributeValueException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java
=======
import ca.ulaval.glo4002.GRAISSE.Trigger.TimedSequentialTrigger;
import ca.ulaval.glo4002.GRAISSE.Trigger.Worker;

>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java
@RunWith(MockitoJUnitRunner.class)
public class TimedSequentialTriggerTest {

	private static final long INVALID_NUMBER_OF_MINUTES = 0;
	private static final long VALID_NUMBER_OF_MINUTES = 1;
	private static final long A_MINUTE = 1;
	private static final long A_MINUTE_IN_MILLISECOND = 60000;
	private static final boolean HAS_JOB_TO_DO = true;
	private static final boolean OBSERVABLE_IS_THE_WORKER = true;
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java

	@Mock
	private Worker mockedWorker;

	@Mock
=======
	
	@Mock
	private Worker mockedWorker;
	
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java
	private TimedSequentialTrigger timedSequentialTrigger;

	@Mock
	private Observable anObservable;

	@Mock
	private TriggerTimerTask mockedTimerTask;

	@Mock
	private TriggerTimer mockedTimer;

	@Before
	public void setUp() {
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java
		ensureThatMockedTimerDoesNotStartANewThread();
		timedSequentialTrigger = spy(new TimedSequentialTrigger(mockedWorker, mockedTimerTask));
	}

	private void ensureThatMockedTimerDoesNotStartANewThread() {
		doNothing().when(mockedTimer).schedule(mockedTimerTask, A_MINUTE_IN_MILLISECOND);
=======
		timedSequentialTrigger = spy(new TimedSequentialTrigger(
				mockedWorker));
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java
	}

	@Test
	public void newTimedSequentialTriggerIsNotRunning() {
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void newTimedSequentialTriggerThrowExceptionWhenSettingInvalidMinutesInterval() throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(INVALID_NUMBER_OF_MINUTES);
	}

	@Test(expected = IllegalStateException.class)
	public void newTimedSequentialTriggerShouldThrowExceptionWHenCallingUnsettedInterval() {
		timedSequentialTrigger.getInterval();
	}

	@Test
	public void newTimedSequentialTriggerShouldReturnSameIntervalThatWasSetBeforeWith() throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(VALID_NUMBER_OF_MINUTES);
		long triggerInterval = timedSequentialTrigger.getInterval();
		assertEquals(VALID_NUMBER_OF_MINUTES, triggerInterval);

	}

	@Test
	public void timedSequentialTriggerShouldStartTimerWhenUpdateMethodIsCalledAndNWorkerHasJobToDo() throws InvalidAttributeValueException {
		when(timedSequentialTrigger.getTimer()).thenReturn(mockedTimer);

		timedSequentialTrigger.setInterval(VALID_NUMBER_OF_MINUTES);

		when(mockedWorker.hasWorkToDO()).thenReturn(HAS_JOB_TO_DO);
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java
		when(timedSequentialTrigger.observableIsTheWorker(anObservable)).thenReturn(OBSERVABLE_IS_THE_WORKER);
=======
		when(timedSequentialTrigger.observableIsTheWorker(anObservable))
				.thenReturn(OBSERVABLE_IS_THE_WORKER);
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java
		timedSequentialTrigger.update(anObservable, null);

		verify(timedSequentialTrigger, times(1)).startTimer();
	}

	@Test
	public void timedSequentialTriggerShouldBeRunningAfterStartTimerHasBeenCalled() throws InvalidAttributeValueException {
		startMockedTimer();

		boolean isRunning = timedSequentialTrigger.isRunning();
		assertTrue(isRunning);
	}

	@Ignore
	@Test
	public void startedTimedSequentialTriggerShouldNotBeRunningAfterReset() throws InvalidAttributeValueException {
		startMockedTimer();

		timedSequentialTrigger.reset();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TimedSequentialTriggerTest.java
	}

	@Test
	public void timedSequentialTriggerShouldCallInitTimerWhenFirstTimeGetTimerIsCalled() throws InvalidAttributeValueException {
		timedSequentialTrigger.getTimer();
		verify(timedSequentialTrigger, times(1)).initTimer();
	}

	@Test
	public void timedSequentialTriggerShouldCallGetTimerOnceWhenStartTimeIsCalled() throws InvalidAttributeValueException {
		startMockedTimer();
		verify(timedSequentialTrigger, times(1)).getTimer();
	}

	@Test(expected = IllegalStateException.class)
	public void timedSequentialTriggerShouldThrowIllegalStateExceptionWhenGetMilliSecondIntervalIsCalledAndTheIntervalHasNotBeenSet() {
		timedSequentialTrigger.getMilliSecondInterval();
	}

	@Test
	public void timedSequentialTriggerShouldReturnTheGoodMilliSecondIntervalAfterTheMinuteIntervalHasBeenSet() throws InvalidAttributeValueException {
		timedSequentialTrigger.setInterval(A_MINUTE);
		long result = timedSequentialTrigger.getMilliSecondInterval();
		assertEquals(A_MINUTE_IN_MILLISECOND, result);
	}

	@Test
	public void timedSequentialTriggerShouldCalledScheduleOfTheTimerOnlyOnceWithTheGoodTimerTaskAndDelay() throws InvalidAttributeValueException {
		startMockedTimer();

		verify(mockedTimer, times(1)).schedule(mockedTimerTask, A_MINUTE_IN_MILLISECOND);
	}

	@Test
	public void timedSequentialTriggerShouldCalledGetTimerTwiceWhenTheResetMethodIsCAlledAndTheTimerIsRunning() throws InvalidAttributeValueException {
		startMockedTimer();
		timedSequentialTrigger.reset();
		verify(timedSequentialTrigger, times(2)).getTimer();
	}

	@Test
	public void timedSequentialTriggerShouldNotCallGetTimerWhenTheResetMethodIsCalledAndTheTimerIsNotRunning() {
		timedSequentialTrigger.reset();
		verify(timedSequentialTrigger, never()).getTimer();
	}

	@Test
	public void timedSequentialTriggerShouldNotCallSetTimerWhenTheResetMethodIsCalledAndTheTimerIsNotRunning() {
		timedSequentialTrigger.reset();
		verify(timedSequentialTrigger, never()).setTimer(any(TriggerTimerStrategy.class));
	}

	@Test
	public void timedSequentialTriggerShouldNotCallGetTimerWhenStartTimerMethodIsCalledAndTheTimerIsAllreadyRunning() throws InvalidAttributeValueException {
		startMockedTimer();
		timedSequentialTrigger.startTimer();
		verify(timedSequentialTrigger, times(1)).getTimer();
	}

	@Test
	public void timedSequentialTriggerShouldCallTimerCancelMethodWhenReseting() throws InvalidAttributeValueException {
		startMockedTimer();
		timedSequentialTrigger.reset();
		verify(mockedTimer, times(1)).cancel();
	}

	@Test
	public void timedSequentialTriggerShouldCallSetTimerToNullWhenResetingAndTheTimerWasRunning() throws InvalidAttributeValueException {
		startMockedTimer();
		timedSequentialTrigger.reset();
		verify(timedSequentialTrigger, times(1)).setTimer(null);
	}

	@Test
	public void timedSequentialTriggerShouldCallSetWorkerOfTimerTaskAtInitialisation() {
		verify(mockedTimerTask, times(1)).setWorker(mockedWorker);
	}

	private void startMockedTimer() throws InvalidAttributeValueException {
		doReturn(mockedTimer).when(timedSequentialTrigger).getTimer();

		timedSequentialTrigger.setInterval(A_MINUTE);
		timedSequentialTrigger.startTimer();
	}
=======
	}

>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TimedSequentialTriggerTest.java
}