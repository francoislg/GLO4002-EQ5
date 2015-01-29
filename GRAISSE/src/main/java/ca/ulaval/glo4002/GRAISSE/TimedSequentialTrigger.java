package ca.ulaval.glo4002.GRAISSE;

import java.util.TimerTask;

import javax.management.InvalidAttributeValueException;

public class TimedSequentialTrigger extends Trigger {

	protected int minutesInterval = 0;
	protected boolean intervalHasBeenSet = false;
	protected boolean isRunning = false;

	public TimedSequentialTrigger(Worker target, TimerTask timerTask) {
		super(target);
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setInterval(int minutes) throws InvalidAttributeValueException {
		if (minutes <= 0) {
			throw new InvalidAttributeValueException("Invalid interval value.");
		}
		minutesInterval = minutes;
		intervalHasBeenSet = true;
	}

	public int getInterval() {
		if (intervalHasBeenSet) {
			return minutesInterval;
		}
		throw new IllegalStateException("The interval has not been set.");
	}

	protected void doUpdatedByWorkerWithWorkToDo() {
		startTimer();
	}

	protected void startTimer() {
		isRunning = true;
	}

	protected void reset() {
		isRunning = false;
	}

}