package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

public class Booker extends Observable implements Worker{

	public void doWork() {
	
	}

	public int numberOfJobsToDo() {
		return 0;
	}

	public boolean hasWorkToDO() {
		return false;
	}
}
