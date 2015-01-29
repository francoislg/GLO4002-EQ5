package ca.ulaval.glo4002.GRAISSE;

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

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsTest {
	private static final String NAMEOFBOARDROOM1 = "Boardroom1";
	private static final String NAMEOFBOARDROOM2 = "Boardroom2";
	private static final String NAMEOFBOARDROOM3 = "Boardroom3";

	private static final String NAMEOFBOARDROOMTHATDOESNOTEXIST = "BoardroomThatDoesNotExist";

	Boardrooms boardrooms;

	@Mock
	Booking booking;

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
		boardrooms.addBoardroom(boardroom1);
		assertFalse(boardrooms.isEmpty());
	}

	@Test
	public void afterAddingOneBoardRoomfindBoardroomWithNameReturnTheBoardroom() throws BoardroomNotFoundExeption {
		addOneBoardroomtoBoardrooms();

		Boardroom boardroom = boardrooms.findBoardroomWithName(NAMEOFBOARDROOM1);
		assertTrue(boardroom.isMyName(NAMEOFBOARDROOM1));

	}

	@Test
	public void afterAddingMultipleBoardroomfindBoardroomWithNameReturnTheBoardroom() throws BoardroomNotFoundExeption {
		addThreeBoardroomtoBoardrooms();

		Boardroom boardroom = boardrooms.findBoardroomWithName(NAMEOFBOARDROOM2);
		assertTrue(boardroom.isMyName(NAMEOFBOARDROOM2));
		boardroom = boardrooms.findBoardroomWithName(NAMEOFBOARDROOM3);
		assertTrue(boardroom.isMyName(NAMEOFBOARDROOM3));
		boardroom = boardrooms.findBoardroomWithName(NAMEOFBOARDROOM1);
		assertTrue(boardroom.isMyName(NAMEOFBOARDROOM1));
	}

	@Test(expected = BoardroomNotFoundExeption.class)
	public void withNoExistingBoardroomWithNamefindBoardroomWithNameThrowBoardroomNotFoundExeption() throws BoardroomNotFoundExeption {
		addThreeBoardroomtoBoardrooms();
		boardrooms.findBoardroomWithName(NAMEOFBOARDROOMTHATDOESNOTEXIST);
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
		setIsMyNameForBoardroomMock(boardroom1, NAMEOFBOARDROOM1);
		setIsMyNameForBoardroomMock(boardroom2, NAMEOFBOARDROOM2);
		setIsMyNameForBoardroomMock(boardroom3, NAMEOFBOARDROOM3);
		boardrooms.addBoardroom(boardroom1);
		boardrooms.addBoardroom(boardroom2);
		boardrooms.addBoardroom(boardroom3);
		when(boardroomsStrategy.format(any())).thenReturn(formatedList);
	}

	private void setIsMyNameForBoardroomMock(Boardroom boardroom, String name) {
		when(boardroom.isMyName(any(String.class))).thenReturn(false);
		when(boardroom.isMyName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {

		when(boardroom1.isMyName(any(String.class))).thenReturn(false);
		when(boardroom1.isMyName(NAMEOFBOARDROOM1)).thenReturn(true);
		boardrooms.addBoardroom(boardroom1);
	}

}
