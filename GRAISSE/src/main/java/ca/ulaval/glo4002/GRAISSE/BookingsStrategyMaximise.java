package ca.ulaval.glo4002.GRAISSE;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class BookingsStrategyMaximise extends BookingsStrategy {

	@Override
	public Collection<Booking> format(Collection<Booking> bookings) {
		Comparator<Booking> byPriorityValue = (e1, e2) -> Integer.compare(e1.getPriorityValue(), e2.getPriorityValue());
		return bookings.stream().sorted(byPriorityValue).collect(Collectors.toList());
	}
}
