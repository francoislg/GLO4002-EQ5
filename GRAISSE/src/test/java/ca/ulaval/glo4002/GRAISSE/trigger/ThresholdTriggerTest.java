package ca.ulaval.glo4002.GRAISSE.trigger;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;

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

	@Test(expected = InvalidThresholdException.class)
	public void thresholdTriggerShouldThrowInvalidThresholdExceptionWhenThresholdIsLesserThanZero() {
		threshold = new ThresholdTrigger(THRESHOLD_LESSER_THAN_ZERO);
	}

	@Test(expected = InvalidThresholdException.class)
	public void thresholdTriggerShouldThrowInvalidThresholdExceptionWhenThresholdIsEqualToZero() {
		threshold = new ThresholdTrigger(THRESHOLD_EQUAL_TO_ZERO);
	}

	@Test
	public void thresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsEqualToThreshold() {
		doReturn(THE_SAME_NUMBER_OF_JOB_AS_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, times(1)).assignBookings();
	}

	@Test
	public void thresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsGreatherThanThreshold() {
		doReturn(A_BIGGER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, times(1)).assignBookings();
	}

	@Test
	public void thresholdTriggerShouldNotCallWorkerDoJobsWhenNumberOfJobsToDoIsLesserThanThreshold() {
		doReturn(A_LESSER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedBooker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(mockedBooker, never()).assignBookings();
	}
	

	private void initValidThresholdTrigger() {
		threshold = new ThresholdTrigger(A_VALID_THRESHOLD);
		threshold.update(mockedBooker);
	}
}
