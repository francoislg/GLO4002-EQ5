package ca.ulaval.glo4002.GRAISSE.Trigger;

<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TriggerTest.java
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

=======
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TriggerTest.java
import java.util.Observable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TriggerTest.java
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

=======
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Trigger.Trigger;
import ca.ulaval.glo4002.GRAISSE.Trigger.Worker;
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TriggerTest.java
@RunWith(MockitoJUnitRunner.class)
public class TriggerTest {

	@Mock
	private Worker mockedWorker;
	private Trigger trigger;
	private static final boolean HAS_NO_WORK_TO_DO = false;
	private static final boolean OBSERVABLE_IS_THE_WORKER = true;

	@Before
	public void setUp() {
		trigger = new Trigger(mockedWorker);
	}

	@Test
	public void whenTriggerTriggTheTargetDoWorkShouldGetCalled() {
		trigger.setOff();
<<<<<<< HEAD:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/TriggerTest.java
		verify(mockedWorker).doWork();
=======
		Mockito.verify(mockedWorker).doWork();
>>>>>>> origin/story4:GRAISSE/src/test/java/ca/ulaval/glo4002/GRAISSE/Trigger/TriggerTest.java
	}

	@Test
	public void whenTriggerTriggTheResetMethodShouldBeCalled() {
		initTriggerHasSpyWithMockedWorker();
		trigger.setOff();
		verify(trigger, times(1)).reset();
	}

	@Test
	public void whenTriggerGetUpdatedByWorkerAndTheWorkerHasNoWorkToDoThenTheResetMethodShouldBeCalled() {
		when(mockedWorker.hasWorkToDO()).thenReturn(HAS_NO_WORK_TO_DO);

		initTriggerHasSpyWithMockedWorker();
		Observable anObservable = new Observable();

		when(trigger.observableIsTheWorker(anObservable)).thenReturn(OBSERVABLE_IS_THE_WORKER);
		trigger.update(anObservable, null);

		verify(trigger, times(1)).reset();
	}

	private void initTriggerHasSpyWithMockedWorker() {
		trigger = spy(new Trigger(mockedWorker));
	}
}
