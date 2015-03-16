package ca.ulaval.glo4002.GRAISSE.boardroom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

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
	public void withNotEnoughtSeatsVerifyNumberOfSeatsReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void withEnoughtSeatsVerifyNumberOfSeatsReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void withSameNamehasNameReturnTrue() {
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test
	public void withDifferentNamehasNameReturnFalse() {
		assertFalse(boardroom.hasName(NAME_NOT_EQUAL_TO_NAME_OF_BOARDROOM_1));
	}
	
	@Test
	public void boardroomShouldBeAvaibleAfterCreation() {
		assertTrue(boardroom.isAvailable());
	}

	@Test
	public void whenBoardroomIsAvailableAndHasEnoughSeatsForTheBookingAssignShouldReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		assertTrue(boardroom.assign(booking));
	}

	@Test
	public void whenBoadroomIsNotAvailableAndHasEnoughSeatsForTheBookingAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(true);
		boardroom.assign(booking);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void whenBoardroomIsAvailableAndHasNotEnoughSeatsForTheBookingAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBER_OF_SEATS_IN_BOARDROOM)).thenReturn(false);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void withSmallerNumberOfSeatsBoardroomcompareByNumberOfSeatsShouldReturnAPositiveNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithLessSeats);
		assertThat(result, greaterThan(0));
	}

	@Test
	public void withBiggerNumberOfSeatsBoardroomcompareByNumberOfSeatsShouldReturnANegativeNumber() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithMoreSeats);
		assertThat(result, lessThan(0));
	}
	
	@Test
	public void withSameNumberOfSeatsBoardroomcompareNumberOfSeatsToBoardroomShouldReturnZero() {
		int result = boardroom.compareByNumberOfSeats(boardroomWithSameSeats);
		assertEquals(0, result);
	}
}
