package ca.ulaval.glo4002.GRAISSE;

import javax.management.InvalidAttributeValueException;

public class TimedSequentialTrigger extends Trigger {

	protected long minutesInterval = 0;
	protected boolean intervalHasBeenSet = false;
	protected boolean isRunning = false;
	protected Timer timer;
	protected TimerTask timerTask;

	protected final long NB_OF_SECOND_IN_A_MINUTE = 60;
	protected final long NB_OF_MILLISECOND_IN_A_SECOND = 1000;

	public TimedSequentialTrigger(Worker target, ca.ulaval.glo4002.GRAISSE.TimerTask timerTask) {
		super(target);
		this.timerTask = timerTask;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setInterval(long minutes) throws InvalidAttributeValueException {
		if (minutes <= 0) {
			throw new InvalidAttributeValueException("Invalid interval value.");
		}
		minutesInterval = minutes;
		intervalHasBeenSet = true;
	}

	public long getInterval() {
		if (!intervalHasBeenSet) {
			throw new IllegalStateException("The interval has not been set.");
		}
		return minutesInterval;
	}

	public long getMilliSecondInterval() {
		return getInterval() * NB_OF_SECOND_IN_A_MINUTE * NB_OF_MILLISECOND_IN_A_SECOND;
	}

	protected void doUpdatedByWorkerWithWorkToDo() {
		startTimer();
	}

	protected void startTimer() {
		if (!isRunning) {
			getTimer().schedule(timerTask, getMilliSecondInterval());
			isRunning = true;
		}
	}

	protected void reset() {
		if (isRunning) {
			getTimer().cancel();
			isRunning = false;
		}
	}

	protected void initTimer() {
		timer = new TimerStrategy();
	}

	protected Timer getTimer() {
		if (timer == null) {
			initTimer();
		}
		return timer;
	}

}