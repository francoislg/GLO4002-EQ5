package ca.ulaval.glo4002.GRAISSE.trigger;

import java.util.HashMap;
import java.util.Timer;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;

public class TimedSequentialTrigger extends Trigger {

	private static final long NB_OF_SECOND_IN_A_MINUTE = 60;
	private static final long NB_OF_MILLISECOND_IN_A_SECOND = 1000;
	private static final int MIN_VALID_NB_OF_MINUTES_INTERVAL = 1;
	
	private long minutesInterval = 0;
	private HashMap<Booker, Timer> scheduledBookers;
	private TimerFactory timerFactory;
	private BookerTimerTaskFactory bookerTimerTaskFactory;

	public TimedSequentialTrigger(long intervalInMinutes, TimerFactory timerFactory,
			BookerTimerTaskFactory bookerTimerTaskFactory) {
		setInterval(intervalInMinutes);
		this.timerFactory = timerFactory;
		this.bookerTimerTaskFactory = bookerTimerTaskFactory;
		scheduledBookers = new HashMap<Booker, Timer>();
	}
	
	private void setInterval(long minutes) {
		if (minutes < MIN_VALID_NB_OF_MINUTES_INTERVAL) {
			throw new InvalidIntervalException();
		}
		minutesInterval = minutes;
	}

	@Override
	public void update(Booker booker) {
		if(booker.hasBookingsToAssign() && !bookerIsAlreadySchedule(booker)) {
			scheduledBookers.put(booker, createScheduleTimer(booker));
		}
		else if(!booker.hasBookingsToAssign() && bookerIsAlreadySchedule(booker)) {
			Timer timer = scheduledBookers.remove(booker);
			timer.cancel();
		}
	}
	
	private Boolean bookerIsAlreadySchedule(Booker booker) {
		return scheduledBookers.containsKey(booker);
	}
	
	private Timer createScheduleTimer(Booker booker) {
		BookerTimerTask bookerTimerTask = bookerTimerTaskFactory.createBookerTimerTask(booker);
		Timer timer = timerFactory.createTimer();
		
		timer.schedule(bookerTimerTask, getMilliSecondInterval());
		return timer;
	}
	
	private long getMilliSecondInterval() {
		return minutesInterval * NB_OF_SECOND_IN_A_MINUTE * NB_OF_MILLISECOND_IN_A_SECOND;
	}
}