package ca.ulaval.glo4002.GRAISSE.Trigger;

public interface Worker {

	public void doWork();

	public boolean hasWorkToDO();

	public int numberOfJobsToDo();
}
