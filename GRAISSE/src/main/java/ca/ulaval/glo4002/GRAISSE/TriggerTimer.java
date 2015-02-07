package ca.ulaval.glo4002.GRAISSE;

public interface TriggerTimer {

	public void schedule(TriggerTimerTask task, long delay);

	public void cancel();
}
