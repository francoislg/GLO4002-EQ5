package ca.ulaval.glo4002.GRAISSE.Persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.BoardroomNotFoundException;
import ca.ulaval.glo4002.GRAISSE.persistence.BoardroomsInMemoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsInMemoryRepositoryTest {

	private static final String NAME_OF_BOARDROOM_1 = "Boardroom1";
	private static final String NAME_OF_BOARDROOM_2 = "Boardroom2";
	private static final String NAME_OF_BOARDROOM_3 = "Boardroom3";
	private BoardroomsInMemoryRepository repoBoardrooms;
	private static final String NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST = "BoardroomThatDoesNotExist";

	@Mock
	Boardroom boardroom1;

	@Mock
	Boardroom boardroom2;

	@Mock
	Boardroom boardroom3;

	@Before
	public void setUp() {
		repoBoardrooms = new BoardroomsInMemoryRepository();
	}

	@Test
	public void isEmptyShouldReturnTrueIfEmpty() {
		assertTrue(repoBoardrooms.isEmpty());
	}

	@Test
	public void afterAddingBoardRoomisEmptyShouldReturnFalse() {
		repoBoardrooms.add(boardroom1);
		assertFalse(repoBoardrooms.isEmpty());
	}

	@Test
	public void afterAddingOneBoardRoomfindBoardroomWithNameReturnTheBoardroom() {
		addOneBoardroomtoBoardrooms();

		Boardroom boardroom = repoBoardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_1);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));

	}

	@Test
	public void afterAddingMultipleBoardroomfindBoardroomWithNameReturnTheBoardroom() {
		addThreeBoardroomtoBoardroomsRespository();

		Boardroom boardroom = repoBoardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_2);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_2));
		boardroom = repoBoardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_3);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_3));
		boardroom = repoBoardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_1);
		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test(expected = BoardroomNotFoundException.class)
	public void withNoExistingBoardroomThrowBoardroomNotFoundExeption() {
		addThreeBoardroomtoBoardroomsRespository();
		repoBoardrooms.findBoardroomWithName(NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST);
	}

	private void addThreeBoardroomtoBoardroomsRespository() {
		setIsMyNameForBoardroomMock(boardroom1, NAME_OF_BOARDROOM_1);
		setIsMyNameForBoardroomMock(boardroom2, NAME_OF_BOARDROOM_2);
		setIsMyNameForBoardroomMock(boardroom3, NAME_OF_BOARDROOM_3);
		repoBoardrooms.add(boardroom1);
		repoBoardrooms.add(boardroom2);
		repoBoardrooms.add(boardroom3);
	}

	private void setIsMyNameForBoardroomMock(Boardroom boardroom, String name) {
		when(boardroom.hasName(any(String.class))).thenReturn(false);
		when(boardroom.hasName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {
		when(boardroom1.hasName(any(String.class))).thenReturn(false);
		when(boardroom1.hasName(NAME_OF_BOARDROOM_1)).thenReturn(true);
		repoBoardrooms.add(boardroom1);
	}

}
