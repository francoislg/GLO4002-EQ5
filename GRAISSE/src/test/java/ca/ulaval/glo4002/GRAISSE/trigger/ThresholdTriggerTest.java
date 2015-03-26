package ca.ulaval.glo4002.GRAISSE.trigger;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;
import ca.ulaval.glo4002.GRAISSE.trigger.exceptions.InvalidThresholdException;

@RunWith(MockitoJUnitRunner.class)
public class ThresholdTriggerTest {

	private static final int A_VALID_THRESHOLD = 1;
	private static final int THRESHOLD_EQUAL_TO_ZERO = 0;
	private static final int THRESHOLD_LESSER_THAN_ZERO = -1;
	private static final int THE_SAME_NUMBER_OF_UNASSIGNED_BOOKINGS_AS_THRESHOLD = 1;
	private static final int A_BIGGER_NUMBER_OF_UNASSIGNED_BOOKINGS_THAN_THRESHOLD = 2;
	private static final int A_LESSER_NUMBER_OF_UNASSIGNED_BOOKINGS_THAN_THRESHOLD = -10;

	@Mock
	Booker booker;

	ThresholdTrigger threshold;

	@Test(expected = InvalidThresholdException.class)
	public void givenThresholdSmallerThanZeroThresholdTriggerShouldThrowInvalidThresholdException() {
		threshold = new ThresholdTrigger(THRESHOLD_LESSER_THAN_ZERO);
	}

	@Test(expected = InvalidThresholdException.class)
	public void givenThresholdEqualToZeroThresholdTriggerShouldThrowInvalidThresholdException() {
		threshold = new ThresholdTrigger(THRESHOLD_EQUAL_TO_ZERO);
	}

	@Test
	public void givenABookerThatHasSameNumberOfUnassignedBookingAsThresholdShouldAssignBookings() {
		doReturn(THE_SAME_NUMBER_OF_UNASSIGNED_BOOKINGS_AS_THRESHOLD).when(booker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(booker).assignBookings();
	}

	@Test
	public void givenABookerThatHasMoreUnassignedBookingThanThresholdShouldAssignBookings() {
		doReturn(A_BIGGER_NUMBER_OF_UNASSIGNED_BOOKINGS_THAN_THRESHOLD).when(booker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(booker).assignBookings();
	}

	@Test
	public void givenABookerThatHasLessUnassignedBookingThanThresholdShouldNotAssignBookings() {
		doReturn(A_LESSER_NUMBER_OF_UNASSIGNED_BOOKINGS_THAN_THRESHOLD).when(booker).numberOfBookingsToAssign();

		initValidThresholdTrigger();

		verify(booker, never()).assignBookings();
	}

	private void initValidThresholdTrigger() {
		threshold = new ThresholdTrigger(A_VALID_THRESHOLD);
		threshold.update(booker);
	}
}