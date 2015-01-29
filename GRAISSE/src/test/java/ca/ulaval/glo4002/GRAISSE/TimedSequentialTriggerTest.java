package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimedSequentialTriggerTest {

	private static final long A_NUMBER = 5;
	private static final long A_LITTLE_NUMBER = 1;

	private TimedSequentialTrigger trigger;

	@Before
	public void setUp() {
		trigger = new TimedSequentialTrigger();
	}

	@Test
	public void WhenCreatingNewObjectShouldHaveDefaultFrequency() {

		assertTrue(trigger.getFrequency() > 0);

	}

	@Test
	public void lastActivationShouldBePositive() {

		assertTrue(trigger.getLastActivation() > 0);

	}

	@Test
	public void areSetAndGetFrequencyWorking() {

		trigger.setFrequency(A_NUMBER);

		assertTrue(trigger.getFrequency() == A_NUMBER);

	}

	@Ignore
	public void triggerShouldActivate() throws InterruptedException {
		trigger.setFrequency(A_LITTLE_NUMBER);

		try {

			Thread.sleep(A_LITTLE_NUMBER);

		} catch (InterruptedException e) {
		}

		assertTrue(trigger.checkActivation());
	}

}
