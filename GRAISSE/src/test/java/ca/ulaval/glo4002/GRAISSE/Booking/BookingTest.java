package ca.ulaval.glo4002.GRAISSE.Booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	private static final int PRIORITYVALUEOFBOOKING = 2;
	private static final int PRIORITYVALUEOFBOOKINGBIGGER = 3;
	private static final int PRIORITYVALUEOFBOOKINGSMALLER = 1;

	private static final int INVALIDPRIORITYVALUEOFBOOKINGBECAUSEITISTOLOW = 0;
	private static final int INVALIDPRIORITYVALUEOFBOOKINGBECAUSEITISTOHIGH = 6;

	private static final int NUMBEROFSEATSINBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISMALLERTHANTHEBOOKING = 9;

	private static final int ANUMBEROFSEATSTHATISEQUALTOTHEBOOKING = 10;

	private static final int ANUMBEROFSEATSTHATISBIGGERTHANTHEBOOKING = 11;

	Booking booking;

	Booking bookingWithBiggerPriority;

	Booking bookingWithSmallerPriority;

	@Before
	public void setUp() throws InvalidPriorityException {
		booking = new Booking(NUMBEROFSEATSINBOOKING);
		booking.setPriority(PRIORITYVALUEOFBOOKING);
		bookingWithBiggerPriority = new Booking(NUMBEROFSEATSINBOOKING);
		bookingWithBiggerPriority.setPriority(PRIORITYVALUEOFBOOKINGBIGGER);
		bookingWithSmallerPriority = new Booking(NUMBEROFSEATSINBOOKING);
		bookingWithSmallerPriority.setPriority(PRIORITYVALUEOFBOOKINGSMALLER);
	}

	@Test(expected = InvalidPriorityException.class)
	public void withInvalidBecauseItIsToHighPriorityValueConstructorShouldThrowInvalidPriorityValue() throws InvalidPriorityException {
		booking = new Booking(NUMBEROFSEATSINBOOKING);
		booking.setPriority(INVALIDPRIORITYVALUEOFBOOKINGBECAUSEITISTOHIGH);
	}

	@Test(expected = InvalidPriorityException.class)
	public void withInvalidBecauseItIsToLowPriorityValueConstructorShouldThrowInvalidPriorityValue() throws InvalidPriorityException {
		booking = new Booking(NUMBEROFSEATSINBOOKING);
		booking.setPriority(INVALIDPRIORITYVALUEOFBOOKINGBECAUSEITISTOLOW);
	}

	@Test
	public void withValidPriorityValueConstructorShouldNotThrowInvalidPriorityValue() throws InvalidPriorityException {
		booking = new Booking(NUMBEROFSEATSINBOOKING);
		booking.setPriority(PRIORITYVALUEOFBOOKING);
	}

	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssigned());
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

	@Test
	public void WithSmallerPriorityBoookingcomparePriorityToBookingShouldReturnAPossitiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithSmallerPriority);
		assertEquals(BIGGER, result);
	}

	@Test
	public void WithBiggerNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnAPossitiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithBiggerPriority);
		assertEquals(SMALLER, result);
	}

}
