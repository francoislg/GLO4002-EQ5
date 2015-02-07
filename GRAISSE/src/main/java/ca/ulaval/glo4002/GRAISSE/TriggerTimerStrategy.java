package ca.ulaval.glo4002.GRAISSE;

import java.util.Timer;

public class TriggerTimerStrategy extends Timer implements TriggerTimer {

	public void schedule(TriggerTimerTask task, long delay) {
		super.schedule((java.util.TimerTask) task, delay);
	}

}
