package ca.ulaval.glo4002.GRAISSE.boardroom;

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

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int NUMBER_OF_SEATS_IN_BOARDROOM = 10;
	private static final int NUMBER_OF_SEATS_SMALLER = 8;
	private static final int NUMBER_OF_SEATS_BIGGER = 11;

	Boardroom boardroom;
	Boardroom boardroomWithMoreSeats;
	Boardroom boardroomWithLessSeats;

	@Mock
	BookingAssignable booking;

	@Before
	public void setUp() {
		boardroom = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_IN_BOARDROOM);
		boardroomWithMoreSeats = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_BIGGER);
		boardroomWithLessSeats = new Boardroom(NAME_OF_BOARDROOM_1, NUMBER_OF_SEATS_SMALLER);
	}

	@Test
	public void getNumberOfSeatsShouldReturnTheNumberOfSeats() {
		assertEquals(boardroom.getNumberOfSeats(), NUMBER_OF_SEATS_IN_BOARDROOM);
	}

	@Test
	public void IfNotEnoughtSeatsVerifyNumberOfSeatsReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfEnoughtSeatsVerifyNumberOfSeatsReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfSameNamehasNameReturnTrue() {
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test
	public void IfNotSameNameIsMyNameReturnFalse() {
		assertFalse(boardroom.hasName(NAME_NOT_EQUAL_TO_NAME_OF_BOARDROOM_1));
	}

	@Test
	public void firstAssignShouldReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.assign(booking));
	}

	@Test
	public void secondAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		boardroom.assign(booking);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void IfVerifyFailassignToBoardroomShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void WithSmallerNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnAPossitiveNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithLessSeats);
		assertEquals(BIGGER, result);
	}

	@Test
	public void WithBiggerNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnAPossitiveNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithMoreSeats);
		assertEquals(SMALLER, result);
	}
}
