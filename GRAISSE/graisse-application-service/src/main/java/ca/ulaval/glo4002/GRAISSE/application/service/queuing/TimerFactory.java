package ca.ulaval.glo4002.GRAISSE.application.service.queuing;

import java.util.Timer;

public class TimerFactory {
	
	public Timer createTimer() {
		return new Timer();
	}
}