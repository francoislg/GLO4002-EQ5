package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;



public class TriggerTest {
	
	final Worker INITIALISATION_TARGET = new Booker();
	final Class<Booker> A_WORKER_IMPLEMENTATION = Booker.class;
	private Trigger trigger;
	final int ZERO = 0;
	
	@Before
	public void setUp() {
		trigger = new Trigger(INITIALISATION_TARGET);
	}
	
	@Test
	public void newTriggerReturnTheSameTargetThatWasPassInBuilder() {
		Worker triggerTarget = trigger.getWorker();
		assertEquals(INITIALISATION_TARGET, triggerTarget);
	}
	
	@Test 
	public void whenTriggerTriggTheTargetDoWorkShouldGetCalled() {
		Worker mockedWorker = Mockito.mock(A_WORKER_IMPLEMENTATION);
		trigger = new Trigger(mockedWorker);
		trigger.trigg();
		Mockito.verify(mockedWorker).doWork();
	}
	
	@Test 
	public void whenTriggerTriggTheResetMethodShouldBeCalled() {
		Worker mockedWorker = Mockito.mock(A_WORKER_IMPLEMENTATION);
		trigger = Mockito.spy(new Trigger(mockedWorker));
		trigger.trigg();
		Mockito.verify(trigger, Mockito.times(1)).reset();
	}
	
	@Test
	public void whenTriggerGetUpdatedByWorkerAndTheWorkerNumberOfJobsToDoIsEqualToZeroTheResetMethodShouldBeCalled() {
		Worker mockedWorker = Mockito.mock(A_WORKER_IMPLEMENTATION);
		Mockito.when(mockedWorker.numberOfJobsToDo()).thenReturn(ZERO);
		
		trigger = Mockito.spy(new Trigger(mockedWorker));
		
		trigger.update((Booker)mockedWorker, null);
		Mockito.verify(trigger, Mockito.times(1)).reset();
	}
	

}
