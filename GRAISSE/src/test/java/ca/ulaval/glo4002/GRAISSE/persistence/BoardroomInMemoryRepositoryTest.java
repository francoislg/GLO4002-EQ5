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

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.exceptions.BoardroomNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomInMemoryRepositoryTest {

	private static final String A_BOARDROOM_NAME = "Boardroom1";
	private static final String A_BOARDROOM_NAME_2 = "Boardroom2";
	private static final String A_BOARDROOM_NAME_3 = "Boardroom3";
	private static final String NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST = "BoardroomThatDoesNotExist";

	@Mock
	Boardroom boardroom;

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

		Boardroom boardroom = repoBoardrooms.retrieve(A_BOARDROOM_NAME);

		assertTrue(boardroom.hasName(A_BOARDROOM_NAME));
	}

	@Test
	public void givenMultipleBoardroomsRetrievingEachBoardroomShouldReturnEachBoardroom() {
		addThreeBoardroomtoBoardroomsRespository();

		Boardroom boardroom1 = repoBoardrooms.retrieve(A_BOARDROOM_NAME);
		Boardroom boardroom2 = repoBoardrooms.retrieve(A_BOARDROOM_NAME_2);
		Boardroom boardroom3 = repoBoardrooms.retrieve(A_BOARDROOM_NAME_3);

		assertTrue(boardroom1.hasName(A_BOARDROOM_NAME));
		assertTrue(boardroom2.hasName(A_BOARDROOM_NAME_2));
		assertTrue(boardroom3.hasName(A_BOARDROOM_NAME_3));
	}

	@Test(expected = BoardroomNotFoundException.class)
	public void givenNoExistingBoardroomRetrivingShouldThrowBoardroomNotFoundExeption() {
		repoBoardrooms.retrieve(NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST);
	}

	private void addThreeBoardroomtoBoardroomsRespository() {
		setBoardroomHasNameMock(boardroom, A_BOARDROOM_NAME);
		setBoardroomHasNameMock(boardroom2, A_BOARDROOM_NAME_2);
		setBoardroomHasNameMock(boardroom3, A_BOARDROOM_NAME_3);

		repoBoardrooms.persist(boardroom);
		repoBoardrooms.persist(boardroom2);
		repoBoardrooms.persist(boardroom3);
	}

	private void setBoardroomHasNameMock(Boardroom boardroom, String name) {
		when(boardroom.hasName(any(String.class))).thenReturn(false);
		when(boardroom.hasName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {
		setBoardroomHasNameMock(boardroom, A_BOARDROOM_NAME_3);
		repoBoardrooms.persist(boardroom);
	}
}