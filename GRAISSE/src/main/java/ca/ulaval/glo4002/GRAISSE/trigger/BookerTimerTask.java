package ca.ulaval.glo4002.GRAISSE.trigger;

import java.util.TimerTask;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;

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