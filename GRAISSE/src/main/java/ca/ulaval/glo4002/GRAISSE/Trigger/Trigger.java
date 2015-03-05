package ca.ulaval.glo4002.GRAISSE.Trigger;

import java.util.Observable;
import java.util.Observer;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public abstract class Trigger implements Observer {

	protected Booker booker;

	public Trigger(Booker worker) {
		this.booker = worker;
	}

	public void setOff() {
		booker.assignBookings();
		reset();
	}

	public void update(Observable o, Object arg) {
		if (observableIsTheWorker(o)) {
			doUpdatedByWorker();
		}
	}

	protected boolean observableIsTheWorker(Observable o) {
		return o == booker;
	}

	protected void doUpdatedByWorker() {
		if (booker.hasWorkToDO()) {
			doUpdatedByWorkerWithWorkToDo();
		} else {
			doUpdatedByWorkerWithNoWorkToDo();
		}
	}

	protected abstract void doUpdatedByWorkerWithWorkToDo();

	protected void doUpdatedByWorkerWithNoWorkToDo() {
		reset();
	}

	protected abstract void reset();

}
