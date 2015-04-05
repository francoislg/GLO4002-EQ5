package ca.ulaval.glo4002.GRAISSE.application.service.queuing;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;

public class BookerTimerTaskFactory {
	
	public BookerTimerTask createBookerTimerTask(Booker booker) {
		return new BookerTimerTask(booker);
	}
}