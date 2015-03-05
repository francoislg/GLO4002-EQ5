package ca.ulaval.glo4002.GRAISSE.Trigger;

import javax.management.InvalidAttributeValueException;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class TimedSequentialTrigger extends Trigger {

	private static final long NB_OF_SECOND_IN_A_MINUTE = 60;
	private static final long NB_OF_MILLISECOND_IN_A_SECOND = 1000;
	private static final int MIN_VALID_NB_OF_MINUTES_INTERVAL = 1;
	private static final long DEFAULT_INTERVAL = 10;
	
	private long minutesInterval = 0;
	private boolean isRunning = false;
	private TriggerTimer timer;
	private TriggerTimerTask timerTask;
	private TriggerTimerStrategyFactory triggerTimerStrategyFactory;



	public TimedSequentialTrigger(Booker target, TriggerTimerTask timerTask) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, DEFAULT_INTERVAL, new TriggerTimerStrategyFactory());
	}
	
	public TimedSequentialTrigger(Booker target, TriggerTimerTask timerTask, TriggerTimerStrategyFactory triggerTimerStrategyFactory) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, DEFAULT_INTERVAL, triggerTimerStrategyFactory);
	}
	
	public TimedSequentialTrigger(Booker target, TriggerTimerTask timerTask, long intervalInMinutes) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, intervalInMinutes, new TriggerTimerStrategyFactory());
	}
	
	public TimedSequentialTrigger(Booker target, TriggerTimerTask timerTask, long intervalInMinutes, TriggerTimerStrategyFactory triggerTimerStrategyFactory) throws InvalidAttributeValueException {
		super(target);
		init(target, timerTask, intervalInMinutes, triggerTimerStrategyFactory);
	}
	
	private void init(Booker target, TriggerTimerTask timerTask, long intervalInMinutes, TriggerTimerStrategyFactory triggerTimerStrategyFactory) throws InvalidAttributeValueException {
		setInterval(intervalInMinutes);
		this.timerTask = timerTask;
		this.timerTask.setBooker(target);
		this.triggerTimerStrategyFactory = triggerTimerStrategyFactory;
	}
	
	private void setInterval(long minutes) throws InvalidAttributeValueException {
		if (minutes < MIN_VALID_NB_OF_MINUTES_INTERVAL) {
			throw new InvalidAttributeValueException("Invalid interval value.");
		}
		minutesInterval = minutes;
	}
	
	private long getMilliSecondInterval() {
		return minutesInterval * NB_OF_SECOND_IN_A_MINUTE * NB_OF_MILLISECOND_IN_A_SECOND;
	}

	public boolean isRunning() {
		return isRunning;
	}

	protected void doUpdatedByWorkerWithWorkToDo() {
		startTimer();
	}

	private void startTimer() {
		if (!isRunning) {
			timer = triggerTimerStrategyFactory.createTimer();
			timer.schedule(timerTask, getMilliSecondInterval());
			isRunning = true;
		}
	}

	protected void reset() {
		if (isRunning) {
			timer.cancel();
			isRunning = false;
		}
	}
}