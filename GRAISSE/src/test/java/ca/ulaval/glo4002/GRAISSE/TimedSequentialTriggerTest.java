package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import javax.management.InvalidAttributeValueException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class TimedSequentialTriggerTest {
	
	final int INVALID_NUMBER_OF_MINUTES = 0;
	final int VALID_NUMBER_OF_MINUTES = 1;
	final Worker INITIALISATION_WORKER = new Booker();
	private TimedSequentialTrigger timedSequentialTrigger;
	final Class<Booker> A_WORKER_IMPLEMENTATION = Booker.class;
	final int NUMBER_OF_JOBS_GREATER_THAN_ZERO = 5;
	
	@Before
	public void setUp() {
		timedSequentialTrigger = Mockito.spy(new TimedSequentialTrigger(INITIALISATION_WORKER));
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
		int triggerInterval = timedSequentialTrigger.getInterval();
		assertEquals(VALID_NUMBER_OF_MINUTES, triggerInterval);
	}
	
	@Test
	public void timedSequentialTriggerShouldStartTimerWhenUpdateMethodIsCalledAndNumberOfWorkerJobsIsGreatherThanZero() throws InvalidAttributeValueException {
		Worker mockedWorker = Mockito.mock(A_WORKER_IMPLEMENTATION);
		Mockito.when(mockedWorker.numberOfJobsToDo()).thenReturn(NUMBER_OF_JOBS_GREATER_THAN_ZERO);
		
		timedSequentialTrigger = Mockito.spy(new TimedSequentialTrigger(mockedWorker));
		timedSequentialTrigger.setInterval(VALID_NUMBER_OF_MINUTES);
		timedSequentialTrigger.update((Booker)mockedWorker, null);
		Mockito.verify(timedSequentialTrigger, Mockito.times(1)).startTimer();
	}
	
	@Test
	public void timedSequentialTriggerShouldBeRunningAfterStartTimerHasBeenCalled() {
		timedSequentialTrigger.startTimer();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertTrue(isRunning);
	}
	
	@Test
	public void startedTimedSequentialTriggerShouldNotBeRunningAfterReset() {
		timedSequentialTrigger.startTimer();
		timedSequentialTrigger.reset();
		boolean isRunning = timedSequentialTrigger.isRunning();
		assertFalse(isRunning);
	}
	
}
