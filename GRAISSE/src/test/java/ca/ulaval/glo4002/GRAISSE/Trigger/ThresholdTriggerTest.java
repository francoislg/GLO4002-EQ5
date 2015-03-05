package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

@RunWith(MockitoJUnitRunner.class)
public class ThresholdTriggerTest {

	@Mock
	private Booker mockedBooker;

	private ThresholdTrigger threshold;

	private static final int A_VALID_THRESHOLD = 1;
	private static final int THRESHOLD_EQUAL_TO_ZERO = 0;
	private static final int THRESHOLD_LESSER_THAN_ZERO = -1;

	private static final int THE_SAME_NUMBER_OF_JOB_AS_THRESHOLD = 1;
	private static final int A_BIGGER_NUMBER_OF_JOBS_THAN_THRESHOLD = 2;
	private static final int A_LESSER_NUMBER_OF_JOBS_THAN_THRESHOLD = -10;

	@Test(expected = IllegalArgumentException.class)
	public void thresholdTriggerShouldThrowExceptionWhenTresholdIsLesserThanZero() {
		threshold = new ThresholdTrigger(mockedBooker, THRESHOLD_LESSER_THAN_ZERO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void thresholdTriggerShouldThrowExceptionWhenTresholdIsEqualToZero() {
		threshold = new ThresholdTrigger(mockedBooker, THRESHOLD_EQUAL_TO_ZERO);
	}

	@Test
	public void tresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsEqualToThreshold() {
		doReturn(THE_SAME_NUMBER_OF_JOB_AS_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, times(1)).assignBookings();
	}

	@Test
	public void tresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsGreatherThanThreshold() {
		doReturn(A_BIGGER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, times(1)).assignBookings();
	}

	@Test
	public void tresholdTriggerShouldNotCallWorkerDoJobsWhenNumberOfJobsToDoIsLesserThanThreshold() {
		doReturn(A_LESSER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, never()).assignBookings();
	}
	
	@Test
	public void whenTriggerTriggTheTargetDoWorkShouldGetCalled() {
		initValidThresholdTrigger(); 
		
		threshold.setOff();

		verify(mockedBooker).assignBookings();
	}

	private void initValidThresholdTrigger() {
		threshold = new ThresholdTrigger(mockedBooker, A_VALID_THRESHOLD);
		threshold.doUpdatedByWorkerWithWorkToDo();
	}
}
