package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.InvalidPriorityException;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	private static final int PRIORITY_VALUE_OF_BOOKING = 2;
	private static final int PRIORITY_VALUE_OF_BOOKING_BIGGER = 3;
	private static final int PRIORITY_VALUE_OF_BOOKING_SMALLER = 1;

	private static final int INVALID_PRIORITY_VALUE_OF_BOOKING_BECAUSE_IT_IS_TO_LOW = 0;
	private static final int INVALID_PRIORITY_VALUE_OF_BOOKING_BECAUSE_IT_IS_TO_HIGH = 6;

	private static final int NUMBER_OF_SEATS_IN_BOOKING = 10;

	private static final int A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING = 9;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING = 10;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING = 11;

	Booking booking;
	Booking bookingWithBiggerPriority;
	Booking bookingWithSmallerPriority;

	@Before
	public void setUp() {
		booking = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(PRIORITY_VALUE_OF_BOOKING);
		bookingWithBiggerPriority = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithBiggerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_BIGGER);
		bookingWithSmallerPriority = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithSmallerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_SMALLER);
	}

	@Test(expected = InvalidPriorityException.class)
	public void withInvalidBecauseItIsToHighPriorityValueConstructorShouldThrowInvalidPriorityValue() {
		booking = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(INVALID_PRIORITY_VALUE_OF_BOOKING_BECAUSE_IT_IS_TO_HIGH);
	}

	@Test(expected = InvalidPriorityException.class)
	public void withInvalidBecauseItIsToLowPriorityValueConstructorShouldThrowInvalidPriorityValue() {
		booking = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(INVALID_PRIORITY_VALUE_OF_BOOKING_BECAUSE_IT_IS_TO_LOW);
	}

	@Test
	public void withValidPriorityValueConstructorShouldNotThrowInvalidPriorityValue() {
		booking = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(PRIORITY_VALUE_OF_BOOKING);
	}

	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssigned());
	}

	@Test
	public void withASmallerNumberOfSeatsVerifyNumberOfSeatsShouldReturnFalse() {
		assertFalse(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING));
	}

	@Test
	public void withTheSameNumberOfSeatsVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING));
	}

	@Test
	public void withABiggerNumberOfSeatsVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING));
	}

	@Test
	public void withSmallerPriorityBoookingComparePriorityToBookingShouldReturnAPositiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithSmallerPriority);
		assertEquals(BIGGER, result);
	}

	@Test
	public void withBiggerNumberOfSeatsBoardroomCompareNumberOfSeatsToBoardroomShouldReturnAPositiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithBiggerPriority);
		assertEquals(SMALLER, result);
	}
}
