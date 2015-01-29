package ca.ulaval.glo4002.GRAISSE;

import java.util.TimerTask;

public class TimerTaskStrategy extends TimerTask implements ca.ulaval.glo4002.GRAISSE.TimerTask {

	private Worker worker;

	public TimerTaskStrategy(Worker worker) {
		this.worker = worker;
	}

	@Override
	public void run() {
		worker.doWork();
	}

}
