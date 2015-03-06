package ca.ulaval.glo4002.GRAISSE.Boardroom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomNotFoundException;
import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Boardroom.BoardroomsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsTest {
	private static final String NAME_OF_BOARDROOM_1 = "Boardroom1";
	private static final String NAME_OF_BOARDROOM_2 = "Boardroom2";
	private static final String NAME_OF_BOARDROOM_3 = "Boardroom3";

	private static final String NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST = "BoardroomThatDoesNotExist";

	Boardrooms boardrooms;

	@Mock
	BookingAssignable booking;

	@Mock
	Boardroom boardroom1;

	@Mock
	Boardroom boardroom2;

	@Mock
	Boardroom boardroom3;

	@Mock
	BoardroomsStrategy boardroomsStrategy;

	@Before
	public void setUp() {
		boardrooms = new Boardrooms();
	}

	@Test
	public void isEmptyShouldReturnTrueIfEmpty() {
		assertTrue(boardrooms.isEmpty());
	}

	@Test
	public void afterAddingBoardRoomisEmptyShouldReturnFalse() {
		boardrooms.add(boardroom1);
		assertFalse(boardrooms.isEmpty());
	}

	@Test
	public void afterAddingOneBoardRoomfindBoardroomWithNameReturnTheBoardroom() throws BoardroomNotFoundException {
		addOneBoardroomtoBoardrooms();

		Boardroom boardroom = boardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_1);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));

	}

	@Test
	public void afterAddingMultipleBoardroomfindBoardroomWithNameReturnTheBoardroom() throws BoardroomNotFoundException {
		addThreeBoardroomtoBoardrooms();

		Boardroom boardroom = boardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_2);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_2));
		boardroom = boardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_3);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_3));
		boardroom = boardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_1);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test(expected = BoardroomNotFoundException.class)
	public void withNoExistingBoardroomWithNamefindBoardroomWithNameThrowBoardroomNotFoundExeption() throws BoardroomNotFoundException {
		addThreeBoardroomtoBoardrooms();
		boardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST);
	}

	@Test
	public void withNoBoardroomThatCanBeAssignToTheBookingassignToBoardroomShouldReturnFalse() {
		addThreeBoardroomtoBoardrooms();
		when(boardroom1.assign(booking)).thenReturn(false);
		when(boardroom2.assign(booking)).thenReturn(false);
		when(boardroom3.assign(booking)).thenReturn(false);
		assertFalse(boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy));
	}

	@Test
	public void withABoardroomThatCanBeAssignToTheBookingassignToBoardroomShouldReturnTrue() {
		addThreeBoardroomtoBoardrooms();
		when(boardroom1.assign(booking)).thenReturn(false);
		when(boardroom2.assign(booking)).thenReturn(false);
		when(boardroom3.assign(booking)).thenReturn(true);
		assertTrue(boardrooms.assignBookingToBoardroom(booking, boardroomsStrategy));
	}

	private void addThreeBoardroomtoBoardrooms() {
		List<Boardroom> formatedList = Arrays.asList(boardroom1, boardroom2, boardroom3);
		setIsMyNameForBoardroomMock(boardroom1, NAME_OF_BOARDROOM_1);
		setIsMyNameForBoardroomMock(boardroom2, NAME_OF_BOARDROOM_2);
		setIsMyNameForBoardroomMock(boardroom3, NAME_OF_BOARDROOM_3);
		boardrooms.add(boardroom1);
		boardrooms.add(boardroom2);
		boardrooms.add(boardroom3);
		when(boardroomsStrategy.sort(any())).thenReturn(formatedList);
	}

	private void setIsMyNameForBoardroomMock(Boardroom boardroom, String name) {
		when(boardroom.hasName(any(String.class))).thenReturn(false);
		when(boardroom.hasName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {

		when(boardroom1.hasName(any(String.class))).thenReturn(false);
		when(boardroom1.hasName(NAME_OF_BOARDROOM_1)).thenReturn(true);
		boardrooms.add(boardroom1);
	}
}
