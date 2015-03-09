package ca.ulaval.glo4002.GRAISSE.Trigger;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class ThresholdTrigger extends Trigger {

	private static final int MINIMUM_THRESHOLD_VALUE = 1;
	private int threshold;

	public ThresholdTrigger(int threshold) {
		if (threshold < MINIMUM_THRESHOLD_VALUE) {
			throw new IllegalArgumentException("The treshold should be greater than zero.");
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
