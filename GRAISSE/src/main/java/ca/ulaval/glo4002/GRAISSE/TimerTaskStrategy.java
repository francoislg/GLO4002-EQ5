package ca.ulaval.glo4002.GRAISSE;

import java.util.TimerTask;

public class TimerTaskStrategy extends TimerTask implements ca.ulaval.glo4002.GRAISSE.TimerTask {

	private Worker worker;

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public void run() {
		if (worker == null) {
			throw new IllegalStateException("Worker was not initialise.");
		}
		worker.doWork();
	}

}
