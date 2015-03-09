package ca.ulaval.glo4002.GRAISSE.Trigger;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public class BookerTimerTaskFactory {
	
	public BookerTimerTask createBookerTimerTask(Booker booker) {
		return new BookerTimerTask(booker);
	}
}
