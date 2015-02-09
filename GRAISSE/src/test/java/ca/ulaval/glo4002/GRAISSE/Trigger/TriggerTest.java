package ca.ulaval.glo4002.GRAISSE.Trigger;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

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

		verify(mockedWorker).doWork();

		Mockito.verify(mockedWorker).doWork();
	}

	@Test
	public void whenTriggerTriggTheResetMethodShouldBeCalled() {
		initTriggerHasSpyWithMockedWorker();
		trigger.setOff();
		verify(trigger, times(1)).reset();
	}

	@Test
	public void whenTriggerGetUpdatedByWorkerAndTheWorkerHasNoWorkToDoThenTheResetMethodShouldBeCalled() {
		doReturn(HAS_NO_WORK_TO_DO).when(mockedWorker).hasWorkToDO();

		initTriggerHasSpyWithMockedWorker();
		Observable anObservable = new Observable();

		doReturn(OBSERVABLE_IS_THE_WORKER).when(trigger).observableIsTheWorker(anObservable);
		trigger.update(anObservable, null);

		verify(trigger, times(1)).reset();
	}

	private void initTriggerHasSpyWithMockedWorker() {
		trigger = spy(new Trigger(mockedWorker));
	}
}
