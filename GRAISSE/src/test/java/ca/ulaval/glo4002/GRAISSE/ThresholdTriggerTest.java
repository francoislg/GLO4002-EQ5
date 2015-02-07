package ca.ulaval.glo4002.GRAISSE;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ThresholdTriggerTest {

	@Mock
	private Worker mockedWorker;

	private ThresholdTrigger threshold;

	private final int A_VALID_THRESHOLD = 1;
	private final int THRESHOLD_EQUAL_TO_ZERO = 0;
	private final int THRESHOLD_LESSER_THAN_ZERO = -1;

	private final int THE_SAME_NUMBER_OF_JOB_AS_THRESHOLD = 1;
	private final int A_BIGGER_NUMBER_OF_JOBS_THAN_THRESHOLD = 2;
	private final int A_LESSER_NUMBER_OF_JOBS_THAN_THRESHOLD = -10;

	@Test(expected = IllegalArgumentException.class)
	public void thresholdTriggerShouldThrowExceptionWhenTresholdIsLesserThanZero() {
		threshold = new ThresholdTrigger(mockedWorker, THRESHOLD_LESSER_THAN_ZERO);
	}

	@Test(expected = IllegalArgumentException.class)
	public void thresholdTriggerShouldThrowExceptionWhenTresholdIsEqualToZero() {
		threshold = new ThresholdTrigger(mockedWorker, THRESHOLD_EQUAL_TO_ZERO);
	}

	@Test
	public void tresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsEqualToThreshold() {
		doReturn(THE_SAME_NUMBER_OF_JOB_AS_THRESHOLD).when(mockedWorker).numberOfJobsToDo();

		initValidThresholdTrigger();

		verify(mockedWorker, times(1)).doWork();
	}

	@Test
	public void tresholdTriggerShouldCallWorkerDoJobsWhenNumberOfJobsToDoIsGreatherThanThreshold() {
		doReturn(A_BIGGER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedWorker).numberOfJobsToDo();

		initValidThresholdTrigger();

		verify(mockedWorker, times(1)).doWork();
	}

	@Test
	public void tresholdTriggerShouldNotCallWorkerDoJobsWhenNumberOfJobsToDoIsLesserThanThreshold() {
		doReturn(A_LESSER_NUMBER_OF_JOBS_THAN_THRESHOLD).when(mockedWorker).numberOfJobsToDo();

		initValidThresholdTrigger();

		verify(mockedWorker, never()).doWork();
	}

	private void initValidThresholdTrigger() {
		threshold = new ThresholdTrigger(mockedWorker, A_VALID_THRESHOLD);
		threshold.doUpdatedByWorkerWithWorkToDo();
	}
}