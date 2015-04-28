package ca.ulaval.glo4002.GRAISSE.context;

public abstract class ContextBase {

	public void apply() {
		registerServices();
		applyFillers();
	}

	protected abstract void registerServices();

	protected abstract void applyFillers();
}
