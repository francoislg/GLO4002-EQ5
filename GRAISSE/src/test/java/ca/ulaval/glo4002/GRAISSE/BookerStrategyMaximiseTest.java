package ca.ulaval.glo4002.GRAISSE;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookerStrategyMaximiseTest {

	BookerStrategyMaximise bookingStrategyMaximise;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Bookings bookings;

	@Before
	public void setUp() {
		bookingStrategyMaximise = new BookerStrategyMaximise();
	}

	@Test
	public void assignBookingsShouldCallMaximiseassignOnBookingsObject() {
		bookingStrategyMaximise.assignBookings(boardrooms, bookings);
		verify(bookings, times(1)).maximiseAssign(boardrooms);
	}
}
