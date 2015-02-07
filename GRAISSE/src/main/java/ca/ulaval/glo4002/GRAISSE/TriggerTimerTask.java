package ca.ulaval.glo4002.GRAISSE;

public interface TriggerTimerTask {

	public boolean cancel();

	public long scheduledExecutionTime();

	public abstract void run();

	public void setWorker(Worker worker);
}
