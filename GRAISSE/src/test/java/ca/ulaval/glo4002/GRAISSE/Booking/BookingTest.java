package ca.ulaval.glo4002.GRAISSE.Booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING = Booking.Priority.LOW;
	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING_BIGGER =Booking.Priority.HIGH;
	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING_SMALLER = Booking.Priority.VERY_LOW;


	private static final int NUMBER_OF_SEATS_IN_BOOKING = 10;

	private static final int A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING = 9;

	private static final int A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING = 10;

	private static final int A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING = 11;

	Booking booking;

	Booking bookingWithBiggerPriority;

	Booking bookingWithSmallerPriority;

	@Before
	public void setUp() throws InvalidPriorityException {
		booking = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(PRIORITY_VALUE_OF_BOOKING);
		bookingWithBiggerPriority = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithBiggerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_BIGGER);
		bookingWithSmallerPriority = new Booking(NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithSmallerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_SMALLER);
	}


	@Test
	public void withValidPriorityValueConstructorShouldNotThrowInvalidPriorityValue() throws InvalidPriorityException {
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
	public void WithSmallerPriorityBookingcomparePriorityToBookingShouldReturnAPositiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithSmallerPriority);
		assertTrue( result > 0);
	}

	@Test
	public void WithBiggerPriorityBookingcomparePriorityToBookingShouldReturnANegativeNumber() {
		int result = booking.comparePriorityToBooking(bookingWithBiggerPriority);
		assertTrue( result < 0);
	}

}
