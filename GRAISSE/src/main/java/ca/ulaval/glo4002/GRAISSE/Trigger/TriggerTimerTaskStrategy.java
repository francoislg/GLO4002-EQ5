package ca.ulaval.glo4002.GRAISSE.Trigger;

import java.util.TimerTask;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class TriggerTimerTaskStrategy extends TimerTask implements TriggerTimerTask {

	private Booker booker;

	@Override
	public void setBooker(Booker booker) {
		this.booker = booker;
	}
	
	@Override
	public void run() {
		if (booker == null) {
			throw new IllegalStateException("Worker was not initialise.");
		}
		booker.doWork();
	}

}
