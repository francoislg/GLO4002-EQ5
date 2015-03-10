package ca.ulaval.glo4002.GRAISSE.trigger;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;
import ca.ulaval.glo4002.GRAISSE.triggerExceptions.InvalidThresholdException;

public class ThresholdTrigger extends Trigger {

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
