package ca.ulaval.glo4002.GRAISSE;


public interface Timer {

	public void schedule(ca.ulaval.glo4002.GRAISSE.TimerTask task, long delay);

	public void cancel();
}
