package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

	private static final Priority PRIORITY_VALUE_OF_BOOKING = Priority.LOW;
	private static final Priority PRIORITY_VALUE_OF_BOOKING_BIGGER = Priority.HIGH;
	private static final Priority PRIORITY_VALUE_OF_BOOKING_SMALLER = Priority.VERY_LOW;
	private static final int NUMBER_OF_SEATS_IN_BOOKING = 10;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING = 9;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING = 10;
	private static final int A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING = 11;

	@Mock
	User user;

	Booking booking;

	@Before
	public void setUp() {
		booking = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING, PRIORITY_VALUE_OF_BOOKING);
	}

	@Test
	public void assignShouldAssignTheBooking() {
		booking.assign();
		assertTrue(booking.isAssigned());
	}

	@Test
	public void givenSameUserAsBookingHasCreatorShouldReturnTrue() {
		assertTrue(booking.hasCreator(user));
	}

	@Test
	public void givenAnotherUserHasCreatorShouldReturnTrue() {
		User another_user = mock(User.class);
		assertFalse(booking.hasCreator(another_user));
	}

	@Test
	public void givenSmallerNumberOfSeatsThanBookingVerifyNumberOfSeatsShouldReturnFalse() {
		assertFalse(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_SMALLER_THAN_THE_BOOKING));
	}

	@Test
	public void givenTheSameNumberOfSeatsThanBookingVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_EQUAL_TO_THE_BOOKING));
	}

	@Test
	public void givenBiggerNumberOfSeatsThanBookingVerifyNumberOfSeatsShouldReturnTrue() {
		assertTrue(booking.verifyNumberOfSeats(A_NUMBER_OF_SEATS_THAT_IS_BIGGER_THAN_THE_BOOKING));
	}

	@Test
	public void givenBookingABookingWithSmallerPriorityWhenComparingShouldReturnAPositiveNumber() {
		Booking bookingWithSmallerPriority = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING, PRIORITY_VALUE_OF_BOOKING_SMALLER);

		int result = booking.comparePriorityToBooking(bookingWithSmallerPriority);

		assertThat(result, greaterThan(0));
	}

	@Test
	public void givenBookingABookingWithBiggerPriorityWhenComparingShouldReturnANegativeNumber() {
		Booking bookingWithBiggerPriority = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING, PRIORITY_VALUE_OF_BOOKING_BIGGER);

		int result = booking.comparePriorityToBooking(bookingWithBiggerPriority);

		assertThat(result, lessThan(0));
	}

	@Test
	public void givenBookingABookingWithSamePriorityWhenComparingShouldReturnZero() {
		Booking bookingWithEqualPriority = new Booking(user, NUMBER_OF_SEATS_IN_BOOKING, PRIORITY_VALUE_OF_BOOKING);

		int result = booking.comparePriorityToBooking(bookingWithEqualPriority);

		assertEquals(0, result);
	}
}