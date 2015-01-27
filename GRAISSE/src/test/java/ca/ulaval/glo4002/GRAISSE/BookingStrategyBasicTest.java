package ca.ulaval.glo4002.GRAISSE;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingStrategyBasicTest {
	private static final int NUMBEROFSEATSSMALLERTHANAVALAIBLE = 9;

	BookingStrategyBasic bookingStrategyBasicTest;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Bookings bookings;

	@Before
	public void setUp() {
		bookingStrategyBasicTest = new BookingStrategyBasic(boardrooms);
	}

	@Test
	public void ifAssignReturnTrueassignBookingsShouldRemoveTheBooking() {

	}
}