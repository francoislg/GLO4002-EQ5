package ca.ulaval.glo4002.GRAISSE;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class TriggerTest {

	private Worker mockedWorker;
	private Trigger trigger;
	private static final boolean HAS_NO_WORK_TO_DO = false;
	private static final boolean OBSERVABLE_IS_THE_WORKER = true;

	@Before
	public void setUp() {
		mockedWorker = Mockito.mock(Worker.class);
		trigger = new Trigger(mockedWorker);
	}

	@Test
	public void whenTriggerTriggTheTargetDoWorkShouldGetCalled() {
		trigger.setOff();
		Mockito.verify(mockedWorker).doWork();
	}

	@Test
	public void whenTriggerTriggTheResetMethodShouldBeCalled() {
		initTriggerHasSpyWithMockedWorker();
		trigger.setOff();
		Mockito.verify(trigger, Mockito.times(1)).reset();
	}

	@Test
	public void whenTriggerGetUpdatedByWorkerAndTheWorkerHasNoWorkToDoThenTheResetMethodShouldBeCalled() {
		Mockito.when(mockedWorker.hasWorkToDO()).thenReturn(HAS_NO_WORK_TO_DO);

		initTriggerHasSpyWithMockedWorker();
		Observable anObservable = new Observable();

		Mockito.when(trigger.observableIsTheWorker(anObservable)).thenReturn(
				OBSERVABLE_IS_THE_WORKER);
		trigger.update(anObservable, null);

		Mockito.verify(trigger, Mockito.times(1)).reset();
	}

	private void initTriggerHasSpyWithMockedWorker() {
		trigger = Mockito.spy(new Trigger(mockedWorker));
	}

}
