package ca.ulaval.glo4002.GRAISSE.application.service.queuing;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.application.service.queuing.exception.InvalidThresholdException;
import ca.ulaval.glo4002.GRAISSE.core.shared.Observer;

public class ThresholdTrigger implements Observer<Booker> {

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