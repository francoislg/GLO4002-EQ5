package ca.ulaval.glo4002.GRAISSE.core.boardroom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomTest {

	private static final String NAME_OF_BOARDROOM_1 = "Boardroom1";
	private static final String NAME_NOT_EQUAL_TO_NAME_OF_BOARDROOM_1 = "Boardroom1Different";
	private static final int NUMBER_OF_SEATS_IN_BOARDROOM = 10;
	private static final int NUMBER_OF_SEATS_SMALLER = 8;
	private static final int NUMBER_OF_SEATS_BIGGER = 11;

	@Mock
	BookingAssignable booking;

	@Mock
	InterfaceReservationBoardroom interfaceReservationBoardroom;

	Boardroom boardroom;

	Boardroom boardroomWithMoreSeats;

	Boardroom boardroomWithLessSeats;

	Boardroom boardroomWithSameSeats;

	@Before
	public void setUp() {
		boardroom = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_IN_BOARDROOM);
		boardroomWithMoreSeats = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_BIGGER);
		boardroomWithLessSeats = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_SMALLER);
		boardroomWithSameSeats = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_IN_BOARDROOM);
	}

	@Test
	public void getNumberOfSeatsShouldReturnTheNumberOfSeats() {
		assertEquals(boardroom.getNumberOfSeats(), NUMBER_OF_SEATS_IN_BOARDROOM);
	}

	@Test
	public void givenInsufficentSeatsWhenVerifyingNumberOfSeatsShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void givenEnoughtSeatsWhenVerifyingNumberOfSeatsShouldReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void givenSameNameHasNameWithShouldReturnTrue() {
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test
	public void givenDifferentNameHasNameShouldReturnFalse() {
		assertFalse(boardroom.hasName(NAME_NOT_EQUAL_TO_NAME_OF_BOARDROOM_1));
	}

	@Test
	public void whenBoardroomIsAvailableAndHasEnoughSeatsForTheBookingAssignShouldReturnTrue() {
		when(interfaceReservationBoardroom.isAvailable(boardroom)).thenReturn(true);
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.assign(booking, interfaceReservationBoardroom));
	}

	@Test
	public void whenBoadroomIsNotAvailableAndHasEnoughSeatsForTheBookingAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);

		boardroom.assign(booking, interfaceReservationBoardroom);

		assertFalse(boardroom.assign(booking, interfaceReservationBoardroom));
	}

	@Test
	public void givenBoardroomIsAvailableAndHasInsufficentSeatsForTheBookingWhenAssigningShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.assign(booking, interfaceReservationBoardroom));
	}

	@Test
	public void givenLessNumberOfSeatsThanBoardroomComparingNumberOfSeatsShouldReturnAPositiveNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithLessSeats);
		assertThat(result, greaterThan(0));
	}

	@Test
	public void givenMoreNumberOfSeatsThanBoardroomComparingNumberOfSeatsShouldReturnANegativeNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithMoreSeats);
		assertThat(result, lessThan(0));
	}

	@Test
	public void givenSameNumberOfSeatsThanBoardroomComparingNumberOfSeatsShouldReturnZero() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithSameSeats);
		assertEquals(0, result);
	}
}
