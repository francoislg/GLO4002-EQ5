package ca.ulaval.glo4002.GRAISSE.Trigger;

import ca.ulaval.glo4002.GRAISSE.Booker.Booker;

public interface TriggerTimerTask {

	public boolean cancel();

	public long scheduledExecutionTime();

	public abstract void run();

	public void setBooker(Booker booker);
}
