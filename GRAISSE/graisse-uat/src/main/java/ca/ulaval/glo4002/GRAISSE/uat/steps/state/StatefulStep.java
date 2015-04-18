package ca.ulaval.glo4002.GRAISSE.uat.steps.state;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.annotations.BeforeScenario;

public class StatefulStep<T extends StepState> {

	private static ThreadLocal<Map<Class<?>, Object>> perThreadState = new ThreadLocal<>();

	static {
		perThreadState.set(new HashMap<>());
	}

	protected T getInitialState() {
		return null;
	}

	@BeforeScenario
	public void createState() {
		T initialState = getInitialState();
		if (initialState != null) {
			perThreadState.get().put(getClass(), initialState);
		}
	}

	@SuppressWarnings("unchecked")
	protected T state() {
		return (T) perThreadState.get().get(getClass());
	}
}