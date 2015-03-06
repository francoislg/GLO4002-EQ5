package ca.ulaval.glo4002.GRAISSE.Trigger;

import java.util.Observable;
import java.util.Observer;

public class Trigger implements Observer {

	protected Worker worker;

	public Trigger(Worker worker) {
		this.worker = worker;
	}

	public void setOff() {
		worker.doWork();
		reset();
	}

	public void update(Observable o, Object arg) {
		if (observableIsTheWorker(o)) {
			doUpdatedByWorker();
		}
	}

	protected boolean observableIsTheWorker(Observable o) {
		return o == worker;
	}

	protected void doUpdatedByWorker() {
		if (worker.hasWorkToDO()) {
			doUpdatedByWorkerWithWorkToDo();
		} else {
			doUpdatedByWorkerWithNoWorkToDo();
		}
	}

	protected void doUpdatedByWorkerWithWorkToDo() {

	}

	protected void doUpdatedByWorkerWithNoWorkToDo() {
		reset();
	}

	protected void reset() {
	}
}
