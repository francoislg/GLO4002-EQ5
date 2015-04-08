package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.exception.BoardroomNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomInMemoryRepositoryTest {

	private static final String NAME_OF_BOARDROOM_1 = "Boardroom1";
	private static final String NAME_OF_BOARDROOM_2 = "Boardroom2";
	private static final String NAME_OF_BOARDROOM_3 = "Boardroom3";
	private static final String NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST = "BoardroomThatDoesNotExist";

	@Mock
	Boardroom boardroom1;

	@Mock
	Boardroom boardroom2;

	@Mock
	Boardroom boardroom3;

	BoardroomInMemoryRepository repoBoardrooms;

	@Before
	public void setUp() {
		repoBoardrooms = new BoardroomInMemoryRepository();
	}

	@Test
	public void newRepositoryShouldBeEmpty() {
		Collection<Boardroom> result = repoBoardrooms.retrieveAll();
		assertTrue(result.isEmpty());
	}

	@Test
	public void givenOneBoardroomRetrievingBoardroomShouldReturnTheBoardroom() {
		addOneBoardroomtoBoardrooms();

		Boardroom boardroom = repoBoardrooms.retrieve(NAME_OF_BOARDROOM_1);

		assertTrue(boardroom.hasName(NAME_OF_BOARDROOM_1));
	}

	@Test
	public void givenMultipleBoardroomsRetrievingEachBoardroomShouldReturnEachBoardroom() {
		addThreeBoardroomtoBoardroomsRespository();

		Boardroom boardroom1 = repoBoardrooms.retrieve(NAME_OF_BOARDROOM_1);
		Boardroom boardroom2 = repoBoardrooms.retrieve(NAME_OF_BOARDROOM_2);
		Boardroom boardroom3 = repoBoardrooms.retrieve(NAME_OF_BOARDROOM_3);

		assertTrue(boardroom1.hasName(NAME_OF_BOARDROOM_1));
		assertTrue(boardroom2.hasName(NAME_OF_BOARDROOM_2));
		assertTrue(boardroom3.hasName(NAME_OF_BOARDROOM_3));
	}

	@Test(expected = BoardroomNotFoundException.class)
	public void givenNoExistingBoardroomRetrivingShouldThrowBoardroomNotFoundExeption() {
		repoBoardrooms.retrieve(NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST);
	}

	private void addThreeBoardroomtoBoardroomsRespository() {
		setBoardroomHasNameMock(boardroom1, NAME_OF_BOARDROOM_1);
		setBoardroomHasNameMock(boardroom2, NAME_OF_BOARDROOM_2);
		setBoardroomHasNameMock(boardroom3, NAME_OF_BOARDROOM_3);

		repoBoardrooms.persist(boardroom1);
		repoBoardrooms.persist(boardroom2);
		repoBoardrooms.persist(boardroom3);
	}

	private void setBoardroomHasNameMock(Boardroom boardroom, String name) {
		when(boardroom.hasName(any(String.class))).thenReturn(false);
		when(boardroom.hasName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {
		setBoardroomHasNameMock(boardroom1, NAME_OF_BOARDROOM_1);
		repoBoardrooms.persist(boardroom1);
	}
}