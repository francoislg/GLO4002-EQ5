package ca.ulaval.glo4002.GRAISSE.trigger;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;
import ca.ulaval.glo4002.GRAISSE.booker.Trigger;
import ca.ulaval.glo4002.GRAISSE.trigger.exceptions.InvalidThresholdException;

public class ThresholdTrigger implements Trigger {

	private static final int MINIMUM_THRESHOLD_VALUE = 1;
	private int threshold;

	public ThresholdTrigger(int threshold) {
		if (threshold < MINIMUM_THRESHOLD_VALUE) {
			throw new InvalidThresholdException();
		}
		this.threshold = threshold;
	}

	@Override
	public void update(Booker booker) {
		if (booker.numberOfBookingsToAssign() >= threshold) {
			booker.assignBookings();
		}
	}
}
