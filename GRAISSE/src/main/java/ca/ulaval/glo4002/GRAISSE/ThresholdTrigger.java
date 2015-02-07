package ca.ulaval.glo4002.GRAISSE;

public class ThresholdTrigger extends Trigger {

	private final int MINIMUM_THRESHOLD_VALUE = 1;
	private int threshold;

	public ThresholdTrigger(Worker target, int threshold) {
		super(target);

		if (!(threshold >= MINIMUM_THRESHOLD_VALUE)) {
			throw new IllegalArgumentException("The treshold should be greater than zero.");
		}
		this.threshold = threshold;
	}

	protected void doUpdatedByWorkerWithWorkToDo() {
		if (worker.numberOfJobsToDo() >= threshold) {
			worker.doWork();
		}
	}
}
