package ca.ulaval.glo4002.GRAISSE.core.booking;

public enum Priority {
	VERY_LOW(0), LOW(1), MEDIUM(2), HIGH(3), VERY_HIGH(4);

	private final int value;

	private Priority(int value) {
		this.value = value;
	}

	public int compare(Priority priorityToCompare) {
		return Integer.compare(value, priorityToCompare.value);
	}
}