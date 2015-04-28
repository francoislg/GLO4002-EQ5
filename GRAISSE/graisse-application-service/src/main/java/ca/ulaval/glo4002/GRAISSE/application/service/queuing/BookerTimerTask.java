package ca.ulaval.glo4002.GRAISSE.application.service.queuing;

import java.util.TimerTask;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;

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