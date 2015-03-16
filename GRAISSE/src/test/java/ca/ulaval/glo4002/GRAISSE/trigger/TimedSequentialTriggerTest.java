package ca.ulaval.glo4002.GRAISSE.trigger;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Timer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;
import ca.ulaval.glo4002.GRAISSE.trigger.exceptions.InvalidIntervalException;

@RunWith(MockitoJUnitRunner.class)
public class TimedSequentialTriggerTest {
	
	private static final long INVALID_NUMBER_OF_MINUTES = 0;
	private static final long A_VALID_INTERVAL_IN_MINUTES = 10;
	private static final long THE_VALID_INTERVAL_IN_MILLISECONDS = 600000;
	private static final Boolean HAS_BOOKINGS_TO_ASSIGN = true;
	private static final Boolean HAS_NO_BOOKINGS_TO_ASSIGN = false;
	private static final Boolean BOOKERS_ARE_NOT_EQUALS = false;
	
	@Mock
	Booker booker;	
	
	@Mock
	Booker secondBooker;

	@Mock
	BookerTimerTask bookerTimerTask;
	
	@Mock
	BookerTimerTask secondBookerTimerTask;
	
	@Mock
	BookerTimerTaskFactory bookerTimerTaskFactory;

	@Mock
	Timer timer;
	
	@Mock
	TimerFactory timerFactory;
	
	TimedSequentialTrigger timedSequentialTrigger;

	@Before
	public void setUp() {
		doReturn(bookerTimerTask).when(bookerTimerTaskFactory).createBookerTimerTask(booker);
		doReturn(secondBookerTimerTask).when(bookerTimerTaskFactory).createBookerTimerTask(secondBooker);
		doReturn(timer).when(timerFactory).createTimer();
		
		ensureThatMockedTimerDoesNotStartANewThread();
		
		timedSequentialTrigger = new TimedSequentialTrigger(A_VALID_INTERVAL_IN_MINUTES,
			timerFactory, bookerTimerTaskFactory);
	}

	private void ensureThatMockedTimerDoesNotStartANewThread() {
		doNothing().when(timer).schedule(bookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
	}

	@Test(expected = InvalidIntervalException.class)
	public void newTimedSequentialTriggerThrowInvalidIntervalExceptionWhenSettingInvalidMinutesInterval() {
		timedSequentialTrigger = new TimedSequentialTrigger(INVALID_NUMBER_OF_MINUTES,
			timerFactory, bookerTimerTaskFactory);
	}
	
	@Test
	public void timedSequentialTriggerShouldScheduleATimerWithTheMillisecondIntervalThatHeWasInitialiseWithAndTheNewlyCreatedBookerTimerTaskWhenTheBookerHasBookingsToAssignAndHisNotScheduleYet() {
		doReturn(HAS_BOOKINGS_TO_ASSIGN).when(booker).hasBookingsToAssign();
		
		timedSequentialTrigger.update(booker);
		
		verify(timer).schedule(bookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
		verify(timer, never()).cancel();
	}
	
	@Test
	public void timedSequentialTriggerShouldCancelTheTimerWhenScheduledBookerHasNoMoreBookingsToAssign() {
		doReturn(HAS_BOOKINGS_TO_ASSIGN).doReturn(HAS_NO_BOOKINGS_TO_ASSIGN).when(booker).hasBookingsToAssign();
		
		timedSequentialTrigger.update(booker); //schedule the booker
		timedSequentialTrigger.update(booker);
		
		verify(timer).schedule(bookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
		verify(timer).cancel();
	}
	
	@Test
	public void timedSequentialTriggerShouldDoNothingWhenUnscheduledBookerWithNoBookingsToAssignAskForUpdate() {
		doReturn(HAS_NO_BOOKINGS_TO_ASSIGN).when(booker).hasBookingsToAssign();
		
		timedSequentialTrigger.update(booker);
		
		verify(timer, never()).schedule(bookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
		verify(timer, never()).cancel();
	}
	
	@Test
	public void timedSequentialTriggerShouldOnlyScheduleOneTimerWhenBookerIsScheduleAndTheBookerAsBookingsToAssign() {
		doReturn(HAS_BOOKINGS_TO_ASSIGN).when(booker).hasBookingsToAssign();
		
		timedSequentialTrigger.update(booker);
		timedSequentialTrigger.update(booker);
		
		verify(timer).schedule(bookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
		verify(timer, never()).cancel();
	}
	
	@Test
	public void timedSequentialTriggerShouldScheduleASecondTimerWhenGivenTwoDifferentBooker() {
		doReturn(HAS_BOOKINGS_TO_ASSIGN).when(booker).hasBookingsToAssign();
		doReturn(HAS_BOOKINGS_TO_ASSIGN).when(secondBooker).hasBookingsToAssign();
		
		doReturn(BOOKERS_ARE_NOT_EQUALS).when(booker).equals(secondBooker);
		
		timedSequentialTrigger.update(booker);
		timedSequentialTrigger.update(secondBooker);
		
		verify(timer).schedule(secondBookerTimerTask, THE_VALID_INTERVAL_IN_MILLISECONDS);
		verify(timer, never()).cancel();
	}
}