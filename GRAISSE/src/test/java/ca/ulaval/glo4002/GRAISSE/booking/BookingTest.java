package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING = Booking.Priority.LOW;
	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING_BIGGER =Booking.Priority.HIGH;
	private static final Booking.Priority PRIORITY_VALUE_OF_BOOKING_SMALLER = Booking.Priority.VERY_LOW;


	private static final int NUMBER_OF_SEATS_IN_BOOKING = 10;

	private static final int A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING = 9;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING = 10;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING = 11;
	
	@Mock
	User user;

	Booking booking;
	Booking bookingWithBiggerPriority;
	Booking bookingWithSmallerPriority;

	@Before
	public void setUp() {
		booking = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(PRIORITY_VALUE_OF_BOOKING);
		bookingWithBiggerPriority = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithBiggerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_BIGGER);
		bookingWithSmallerPriority = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING);
		bookingWithSmallerPriority.setPriority(PRIORITY_VALUE_OF_BOOKING_SMALLER);
	}
	
	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssigned());
	}

	@Test
	public void withValidPriorityValueConstructorShouldNotThrowInvalidPriorityValue() {
		booking = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING);
		booking.setPriority(PRIORITY_VALUE_OF_BOOKING);
	}

	@Test
	public void hasCreatorShouldReturnTrueForCreator() {
		assertTrue(booking.hasCreator(user));
	}
	
	@Test
	public void hasCreatorShouldReturnTrueForAnotherUser() {
		User another_user = mock(User.class);
		assertFalse(booking.hasCreator(another_user));
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
	public void withSmallerPriorityBookingcomparePriorityToBookingShouldReturnAPositiveNumber() {
		int result = booking.comparePriorityToBooking(bookingWithSmallerPriority);
		assertTrue( result > 0);
	}

	@Test
	public void withBiggerPriorityBookingcomparePriorityToBookingShouldReturnANegativeNumber() {
		int result = booking.comparePriorityToBooking(bookingWithBiggerPriority);
		assertTrue( result < 0);
	}
}
