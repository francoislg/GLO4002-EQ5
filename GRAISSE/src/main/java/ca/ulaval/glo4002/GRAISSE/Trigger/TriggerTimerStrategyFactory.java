package ca.ulaval.glo4002.GRAISSE.Trigger;

public class TriggerTimerStrategyFactory {
	
	public TriggerTimer createTimer()
	{
		return new TriggerTimerStrategy();
	}

}
