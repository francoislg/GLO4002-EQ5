package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;
import java.util.Observer;

public class Trigger implements Observer{

	protected Worker worker;
	
	public Trigger(Worker worker) {
		this.worker = worker;
	}
	
	public Worker getWorker() {
		return worker;
	}
	
	public void trigg() {
		getWorker().doWork();
		reset();
	}

	public void update(Observable o, Object arg) {
		if(o == worker) {
			doUpdatedByWorker();
		}
	}
	
	protected void doUpdatedByWorker() {
		if(worker.numberOfJobsToDo() == 0) {
			reset();
		}
	}
	
	protected void reset() {
	}

}
