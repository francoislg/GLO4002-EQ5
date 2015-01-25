package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import org.junit.Test;


public class TimedSequentialTriggerTest {
	


	@Test
	public void defaultFrequencyIsPositive() {
		
		TimedSequentialTrigger objectToTest = new TimedSequentialTrigger();
		assertTrue(objectToTest.getFrequencyInMinutes()>0);

	}
	
	@Test
	public void lastActivationIsPositive() {
		
		TimedSequentialTrigger objectToTest = new TimedSequentialTrigger();
		assertTrue(objectToTest.lastActivationInMs>0);

	}
	
	@Test
	public void isSetAndGetFrequencyWorking() {
		
		long aNumber = 5 ;
		TimedSequentialTrigger objectToTest = new TimedSequentialTrigger();
		objectToTest.setFrequency(aNumber,true);
		
		assertTrue(objectToTest.getFrequencyInMinutes()==aNumber);

	}
	

	
	@Test
	public void triggerCanActivate() {
		
		long littleFrequencyInMs =  2;
		
		TimedSequentialTrigger objectToTest = new TimedSequentialTrigger();
		objectToTest.setFrequency(littleFrequencyInMs,false);
		
		try{
		    Thread.sleep(littleFrequencyInMs);
		}catch(InterruptedException e){
		    System.out.println("got interrupted!");
		}

		assertTrue(objectToTest.checkActivation());
		
	}
	
	
}
