package ca.ulaval.glo4002.GRAISSE;



public class TimedSequentialTrigger extends Trigger{

	
	private static final long DEFAULT_FREQUENCY= 10;
	
	private long timeFrequencyInMs;
	private long lastActivationInMs;
	
	
	public TimedSequentialTrigger()
	{
		lastActivationInMs=System.currentTimeMillis();
		timeFrequencyInMs = DEFAULT_FREQUENCY*60000;
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
		timeFrequencyInMs = frequency*60000;

	}
	

	
	public long getFrequency()
	{
		return timeFrequencyInMs/60000;
	}
	
	
	public long getLastActivation()
	{
		return lastActivationInMs/60000;
	}
	

	
	
}
