package ca.ulaval.glo4002.GRAISSE.Trigger;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class ThresholdTrigger extends Trigger {

	private static final int MINIMUM_THRESHOLD_VALUE = 1;
	private int threshold;

	public ThresholdTrigger(Booker target, int threshold) {
		super(target);

		if (threshold < MINIMUM_THRESHOLD_VALUE) {
			throw new IllegalArgumentException("The treshold should be greater than zero.");
		}
		this.threshold = threshold;
	}

	protected void doUpdatedByWorkerWithWorkToDo() {
		if (booker.numberOfBookingsToAssign() >= threshold) {
			booker.assignBookings();
		}
	}

	@Override
	protected void reset() {
		//nothing to do
		//Subject to change in issue #26
	}
}
