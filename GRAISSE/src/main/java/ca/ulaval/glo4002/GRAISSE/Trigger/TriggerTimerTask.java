package ca.ulaval.glo4002.GRAISSE.Trigger;

public interface TriggerTimerTask {

	public boolean cancel();

	public long scheduledExecutionTime();

	public abstract void run();

	public void setWorker(Worker worker);
}
