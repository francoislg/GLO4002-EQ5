package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import org.junit.Test;


public class TimedSequentialTriggerTest {
	
	
	private static final long A_NUMBER= 5;
	private static final long A_LITTLE_NUMBER = 1;

	TimedSequentialTrigger trigger;


	public void setUp() {
		trigger = new TimedSequentialTrigger();
	}

	@Test
	public void WhenCreatingNewObjectShouldHaveDefaultFrequency() {
		
		setUp();
		assertTrue(trigger.getFrequency()>0);

	}
	
	@Test
	public void lastActivationShouldBePositive() {
		
		setUp();
		assertTrue(trigger.getLastActivation()>0);

	}
	
	@Test
	public void areSetAndGetFrequencyWorking() {
		
		setUp();
		trigger.setFrequency(A_NUMBER);
		
		assertTrue(trigger.getFrequency() == A_NUMBER);

	}
	

	
	@Test
	public void triggerShouldActivate() {
		
		setUp();
		trigger.setFrequency(A_LITTLE_NUMBER);
		
		try{
			
		    Thread.sleep(A_LITTLE_NUMBER*60000);
		    
		}catch(InterruptedException e){}

		assertTrue(trigger.checkActivation());
		
	}
	
	
}
