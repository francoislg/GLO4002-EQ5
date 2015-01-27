package ca.ulaval.glo4002.GRAISSE;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest extends TestCase {

	private static final int NUMBEROFSEATSINBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISMALLERTHANTHEBOOKING = 9;

	private static final int ANUMBEROFSEATSTHATISEQUALTOTHEBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISBIGGERTHANTHEBOOKING = 11;

	Booking booking;

	@Before
	public void setUp() {
		booking = new Booking(NUMBEROFSEATSINBOOKING);
	}

	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssign());
	}

	@Test
	public void withASmallerNumberOfSeatsVerifyNumberOfSeatsShouldReturnFalse() {
		assertFalse(booking.verifyNumberOfSeats(ANUMBEROFSEATSTHATISMALLERTHANTHEBOOKING));
	}

	@Test
	public void withTheSameNumberOfSeatsVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(ANUMBEROFSEATSTHATISEQUALTOTHEBOOKING));
	}

	@Test
	public void withABiggerNumberOfSeatsVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(ANUMBEROFSEATSTHATISBIGGERTHANTHEBOOKING));
	}
}
