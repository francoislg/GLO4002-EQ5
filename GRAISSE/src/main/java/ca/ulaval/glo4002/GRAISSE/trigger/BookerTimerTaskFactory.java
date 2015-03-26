package ca.ulaval.glo4002.GRAISSE.trigger;

import ca.ulaval.glo4002.GRAISSE.booker.Booker;

public class BookerTimerTaskFactory {
	
	public BookerTimerTask createBookerTimerTask(Booker booker) {
		return new BookerTimerTask(booker);
	}
}