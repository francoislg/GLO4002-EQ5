package ca.ulaval.glo4002.GRAISSE;


public class TimedSequentialTrigger extends Trigger{

	
	private static final long DEFAULT_FREQUENCY_MINUTES = 10;
	
	private long timeFrequencyInMs;
	public long lastActivationInMs;
	
	TimedSequentialTrigger()
	{
		lastActivationInMs=System.currentTimeMillis();
		timeFrequencyInMs = DEFAULT_FREQUENCY_MINUTES*60000;
	}
	
	public boolean checkActivation()
	{
		long actualTime = System.currentTimeMillis();
		if( (actualTime - lastActivationInMs)>= timeFrequencyInMs )
		{
			lastActivationInMs=actualTime;
			return true;
			
		}
		return false;
	}
	
	public void setFrequency(long frequency)
	{
		timeFrequencyInMs = frequency*60*1000;

	}
	

	public long getFrequency()
	{
		return timeFrequencyInMs/60000;
	}
	
	

	

	
	
}
