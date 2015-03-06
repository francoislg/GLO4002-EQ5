package ca.ulaval.glo4002.GRAISSE.Trigger;

import java.util.TimerTask;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class BookerTimerTask extends TimerTask {

	private Booker booker;

	public BookerTimerTask(Booker booker) {
		this.booker = booker;
	}
	
	@Override
	public void run() {
		booker.assignBookings();
	}

}
