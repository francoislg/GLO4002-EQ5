package ca.ulaval.glo4002.GRAISSE.Trigger;

import javax.management.InvalidAttributeValueException;

public class TimedSequentialTrigger extends Trigger {

	protected long minutesInterval = 0;
	protected boolean intervalHasBeenSet = false;
	protected boolean isRunning = false;
	protected TriggerTimer timer;
	protected TriggerTimerTask timerTask;

	protected final long NB_OF_SECOND_IN_A_MINUTE = 60;
	protected final long NB_OF_MILLISECOND_IN_A_SECOND = 1000;
	protected final int MIN_VALID_NB_OF_MINUTES_INTERVAL = 1;
	protected final long DEFAULT_INTERVAL = 10;

	public TimedSequentialTrigger(Worker target, TriggerTimerTask timerTask) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, DEFAULT_INTERVAL);
	}
	
	public TimedSequentialTrigger(Worker target, TriggerTimerTask timerTask, long intervalInMinutes) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, intervalInMinutes);
	}
	
	protected void init(Worker target, TriggerTimerTask timerTask, long intervalInMinutes) throws InvalidAttributeValueException {
		setInterval(intervalInMinutes);
		this.timerTask = timerTask;
		this.timerTask.setWorker(target);
	}
	
	protected void setInterval(long minutes) throws InvalidAttributeValueException {
		if (minutes < MIN_VALID_NB_OF_MINUTES_INTERVAL) {
			throw new InvalidAttributeValueException("Invalid interval value.");
		}
		minutesInterval = minutes;
		intervalHasBeenSet = true;
	}

	protected long getInterval() {
		if (!intervalHasBeenSet) {
			throw new IllegalStateException("The interval has not been set.");
		}
		return minutesInterval;
	}
	
	protected long getMilliSecondInterval() {
		return getInterval() * NB_OF_SECOND_IN_A_MINUTE * NB_OF_MILLISECOND_IN_A_SECOND;
	}

	public boolean isRunning() {
		return isRunning;
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
			setTimer(null);
			isRunning = false;
		}
	}

	protected void initTimer() {
		timer = new TriggerTimerStrategy();
	}

	protected TriggerTimer getTimer() {
		if (timer == null) {
			initTimer();
		}
		return timer;
	}

	protected TimedSequentialTrigger setTimer(TriggerTimerStrategy timer) {
		this.timer = timer;
		return this;
	}
}