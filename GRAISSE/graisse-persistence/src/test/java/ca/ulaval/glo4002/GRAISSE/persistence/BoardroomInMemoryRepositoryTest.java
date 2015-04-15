package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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

	private static final String A_BOARDROOM_NAME = "Boardroom1";
	private static final String A_BOARDROOM_NAME_2 = "Boardroom2";
	private static final String A_BOARDROOM_NAME_3 = "Boardroom3";
	private static final String NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST = "BoardroomThatDoesNotExist";

	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

	@Mock
	Boardroom boardroom;

	@Mock
	Boardroom boardroom2;

	@Mock
	Boardroom boardroom3;

	@Mock
	Boardroom boardroomWithLeastNumberOfSeats;

	@Mock
	Boardroom boardroomWithSecondLeastNumberOfSeats;

	@Mock
	Boardroom boardroomWithThirdLeastNumberOfSeats;

	@Mock
	Boardroom boardroomWithMostNumberOfSeats;

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

	@Test
	public void whenPersistingTwiceTheSameBoardroomTheRepositoryShouldContainOneBoardroom() {
		repoBoardrooms.persist(boardroom);
		repoBoardrooms.persist(boardroom);

		int resultSize = repoBoardrooms.retrieveAll().size();
		assertEquals(1, resultSize);
	}

	@Test
	public void givenUnorderedListWhenSortingWithdOrderByNumberOfSeatsShouldReturnOrderedByNumberOfSeats() {
		setUpMocksForMultipleBoardroomsWithSeats();
		setUpRepositoryWithUnorderedListOfBoardrooms();
		Collection<Boardroom> expectedBoardroomList = orderedListOfBoardroomsByNumberOfSeats();

		Collection<Boardroom> result = repoBoardrooms.retrieveBoardroomsOrderedByNumberOfSeats();

		assertEquals(expectedBoardroomList, result);
	}

	@Test
	public void givenAListOfBoardroomWhenSortingDefaultShouldReturnSameList() {
		Collection<Boardroom> boardroomCollection = orderedListOfBoardroomsByNumberOfSeats();
		setUpRepositoryWithOrderedListOfBoardrooms();
		assertEquals(boardroomCollection, repoBoardrooms.retrieveAll());
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
		setBoardroomHasNameMock(boardroom, A_BOARDROOM_NAME);
		repoBoardrooms.persist(boardroom);
	}

	private void setUpMocksForMultipleBoardroomsWithSeats() {
		when(boardroomWithLeastNumberOfSeats.compareByNumberOfSeats(any())).thenReturn(SMALLER);

		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithLeastNumberOfSeats)).thenReturn(BIGGER);
		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithMostNumberOfSeats)).thenReturn(SMALLER);
		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithThirdLeastNumberOfSeats)).thenReturn(EQUAL);

		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithLeastNumberOfSeats)).thenReturn(BIGGER);
		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithMostNumberOfSeats)).thenReturn(SMALLER);
		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithSecondLeastNumberOfSeats)).thenReturn(EQUAL);

		when(boardroomWithMostNumberOfSeats.compareByNumberOfSeats(any())).thenReturn(BIGGER);
	}

	private void setUpRepositoryWithUnorderedListOfBoardrooms() {
		repoBoardrooms.persist(boardroomWithSecondLeastNumberOfSeats);
		repoBoardrooms.persist(boardroomWithMostNumberOfSeats);
		repoBoardrooms.persist(boardroomWithThirdLeastNumberOfSeats);
		repoBoardrooms.persist(boardroomWithLeastNumberOfSeats);
	}

	private void setUpRepositoryWithOrderedListOfBoardrooms() {
		repoBoardrooms.persist(boardroomWithLeastNumberOfSeats);
		repoBoardrooms.persist(boardroomWithSecondLeastNumberOfSeats);
		repoBoardrooms.persist(boardroomWithThirdLeastNumberOfSeats);
		repoBoardrooms.persist(boardroomWithMostNumberOfSeats);
	}

	private Collection<Boardroom> orderedListOfBoardroomsByNumberOfSeats() {
		return Arrays.asList(boardroomWithLeastNumberOfSeats, boardroomWithSecondLeastNumberOfSeats, boardroomWithThirdLeastNumberOfSeats,
				boardroomWithMostNumberOfSeats);
	}
}