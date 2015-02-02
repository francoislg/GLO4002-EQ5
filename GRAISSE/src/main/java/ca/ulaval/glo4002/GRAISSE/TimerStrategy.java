package ca.ulaval.glo4002.GRAISSE;

import java.util.Timer;

public class TimerStrategy extends Timer implements ca.ulaval.glo4002.GRAISSE.Timer {

	public void schedule(TimerTask task, long delay) {
		super.schedule((java.util.TimerTask) task, delay);
	}

}
