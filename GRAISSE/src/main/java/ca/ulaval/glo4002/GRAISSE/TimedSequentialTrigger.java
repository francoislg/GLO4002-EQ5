package ca.ulaval.glo4002.GRAISSE;



public class TimedSequentialTrigger extends Trigger{

	
	private static final long DEFAULT_FREQUENCY= 10;
	private static final long MINUTES_TO_MS= 60000;

	private long timeFrequencyInMs;
	private long lastActivationInMs;
	
	
	public TimedSequentialTrigger()
	{
		lastActivationInMs=System.currentTimeMillis();
		timeFrequencyInMs = DEFAULT_FREQUENCY*MINUTES_TO_MS;
	}
	
	public boolean checkActivation()
	{
		long actualTimeInMs = System.currentTimeMillis();
		if( (actualTimeInMs - lastActivationInMs)>= timeFrequencyInMs )
		{
			lastActivationInMs=actualTimeInMs;
			return true;
			
		}
		return false;
	}
	
	public void setFrequency(long frequency)
	{
		timeFrequencyInMs = frequency*MINUTES_TO_MS;

	}
	

	
	public long getFrequency()
	{
		return timeFrequencyInMs/MINUTES_TO_MS;
	}
	
	
	public long getLastActivation()
	{
		return lastActivationInMs/MINUTES_TO_MS;
	}
	

	
	
}
