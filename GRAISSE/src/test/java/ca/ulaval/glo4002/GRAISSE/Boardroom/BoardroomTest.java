package ca.ulaval.glo4002.GRAISSE.Boardroom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardroom;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomTest {

	private static final String NAME_OF_BOARD_ROOM_1 = "Boardroom1";

	private static final String NAME_NOT_EQUAL_TO_NAME_OF_BOARD_ROOM_1 = "Boardroom1Different";

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	private static final int NUMBER_OF_SEATS_IN_BOARD_ROOM = 10;

	private static final int NUMBER_OF_SEATS_SMALLER = 8;

	private static final int NUMBER_OF_SEATS_BIGGER = 11;

	Boardroom boardroom;

	Boardroom boardroomWithMoreSeats;

	Boardroom boardroomWithLessSeats;

	@Mock
	BookingAssignable booking;

	@Before
	public void setUp() {
		boardroom = new Boardroom(NAME_OF_BOARD_ROOM_1, NUMBER_OF_SEATS_IN_BOARD_ROOM);
		boardroomWithMoreSeats = new Boardroom(NAME_OF_BOARD_ROOM_1, NUMBER_OF_SEATS_BIGGER);
		boardroomWithLessSeats = new Boardroom(NAME_OF_BOARD_ROOM_1, NUMBER_OF_SEATS_SMALLER);
	}

	@Test
	public void getNumberOfSeatsShouldReturnTheNumberOfSeats() {

		assertEquals(boardroom.getNumberOfSeats(), NUMBER_OF_SEATS_IN_BOARD_ROOM);
	}

	@Test
	public void IfNotEnoughtSeatsVerifyNumberOfSeatsReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARD_ROOM)).thenReturn(false);
		assertFalse(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfEnoughtSeatsVerifyNumberOfSeatsReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARD_ROOM)).thenReturn(true);
		assertTrue(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfSameNameIsMyNameReturnTrue() {
		assertTrue(boardroom.isMyName(NAME_OF_BOARD_ROOM_1));
	}

	@Test
	public void IfNotSameNameIsMyNameReturnFalse() {
		assertFalse(boardroom.isMyName(NAME_NOT_EQUAL_TO_NAME_OF_BOARD_ROOM_1));
	}

	@Test
	public void firstAssignShouldReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARD_ROOM)).thenReturn(true);
		assertTrue(boardroom.assign(booking));
	}

	@Test
	public void secondAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARD_ROOM)).thenReturn(true);
		boardroom.assign(booking);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void IfVerifyFailassignToBoardroomShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARD_ROOM)).thenReturn(false);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void WithSmallerNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnAPossitiveNumber() {
		int result = boardroom.compareNumberOfSeatsToBoardroomNumberOfSeats(boardroomWithLessSeats);
		assertEquals(BIGGER, result);
	}

	@Test
	public void WithBiggerNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnAPossitiveNumber() {
		int result = boardroom.compareNumberOfSeatsToBoardroomNumberOfSeats(boardroomWithMoreSeats);
		assertEquals(SMALLER, result);
	}

}