package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final int PRIORITYVALUEOFBOOKING = 2;

	private static final int INVALIDPRIORITYVALUEOFBOOKING = 6;

	private static final int NUMBEROFSEATSINBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISMALLERTHANTHEBOOKING = 9;

	private static final int ANUMBEROFSEATSTHATISEQUALTOTHEBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISBIGGERTHANTHEBOOKING = 11;

	Booking booking;

	@Before
	public void setUp() throws InvalidPriorityValue {
		booking = new Booking(NUMBEROFSEATSINBOOKING, PRIORITYVALUEOFBOOKING);
	}

	@Test(expected = InvalidPriorityValue.class)
	public void withInvalidPriorityValueConstructorShouldThrowInvalidPriorityValue() throws InvalidPriorityValue {
		booking = new Booking(NUMBEROFSEATSINBOOKING, INVALIDPRIORITYVALUEOFBOOKING);
	}

	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssign());
	}

	@Test
	public void getPriorityValueShouldReturnThePriorityValue() {
		assertEquals(PRIORITYVALUEOFBOOKING, booking.getPriorityValue());
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
