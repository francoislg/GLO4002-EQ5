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

	BoardroomInMemoryRepository boardroomInMemoryRepository;

	@Before
	public void setUp() {
		boardroomInMemoryRepository = new BoardroomInMemoryRepository();
	}

	@Test
	public void newRepositoryShouldBeEmpty() {
		Collection<Boardroom> result = boardroomInMemoryRepository.retrieveAll();
		assertTrue(result.isEmpty());
	}

	@Test
	public void givenOneBoardroomRetrievingBoardroomShouldReturnTheBoardroom() {
		addOneBoardroomtoBoardrooms();

		Boardroom boardroom = boardroomInMemoryRepository.retrieve(A_BOARDROOM_NAME);

		assertTrue(boardroom.hasName(A_BOARDROOM_NAME));
	}

	@Test
	public void givenMultipleBoardroomsRetrievingEachBoardroomShouldReturnEachBoardroom() {
		addThreeBoardroomtoBoardroomsRespository();
		Boardroom boardroom1 = boardroomInMemoryRepository.retrieve(A_BOARDROOM_NAME);
		Boardroom boardroom2 = boardroomInMemoryRepository.retrieve(A_BOARDROOM_NAME_2);
		Boardroom boardroom3 = boardroomInMemoryRepository.retrieve(A_BOARDROOM_NAME_3);

		assertTrue(boardroom1.hasName(A_BOARDROOM_NAME));
		assertTrue(boardroom2.hasName(A_BOARDROOM_NAME_2));
		assertTrue(boardroom3.hasName(A_BOARDROOM_NAME_3));
	}

	@Test(expected = BoardroomNotFoundException.class)
	public void givenNoExistingBoardroomRetrivingShouldThrowBoardroomNotFoundExeption() {
		boardroomInMemoryRepository.retrieve(NAME_OF_BOARDROOM_THAT_DOES_NOT_EXIST);
	}

	@Test
	public void whenPersistingTwiceTheSameBoardroomTheRepositoryShouldContainOneBoardroom() {
		boardroomInMemoryRepository.persist(boardroom);
		boardroomInMemoryRepository.persist(boardroom);

		int resultSize = boardroomInMemoryRepository.retrieveAll().size();
		assertEquals(1, resultSize);
	}

	@Test
	public void givenUnorderedListWhenSortingWithdOrderByNumberOfSeatsShouldReturnOrderedByNumberOfSeats() {
		setUpMocksForMultipleBoardroomsWithSeats();
		setUpRepositoryWithUnorderedListOfBoardrooms();
		Collection<Boardroom> expectedBoardroomList = orderedListOfBoardroomsByNumberOfSeats();

		Collection<Boardroom> result = boardroomInMemoryRepository.retrieveBoardroomsOrderedByNumberOfSeats();

		assertEquals(expectedBoardroomList, result);
	}

	@Test
	public void givenAListOfBoardroomWhenSortingDefaultShouldReturnSameList() {
		Collection<Boardroom> boardroomCollection = orderedListOfBoardroomsByNumberOfSeats();
		setUpRepositoryWithOrderedListOfBoardrooms();
		assertEquals(boardroomCollection, boardroomInMemoryRepository.retrieveAll());
	}

	private void addThreeBoardroomtoBoardroomsRespository() {
		setBoardroomHasNameMock(boardroom, A_BOARDROOM_NAME);
		setBoardroomHasNameMock(boardroom2, A_BOARDROOM_NAME_2);
		setBoardroomHasNameMock(boardroom3, A_BOARDROOM_NAME_3);

		boardroomInMemoryRepository.persist(boardroom);
		boardroomInMemoryRepository.persist(boardroom2);
		boardroomInMemoryRepository.persist(boardroom3);
	}

	private void setBoardroomHasNameMock(Boardroom boardroom, String name) {
		when(boardroom.hasName(any(String.class))).thenReturn(false);
		when(boardroom.hasName(name)).thenReturn(true);
	}

	private void addOneBoardroomtoBoardrooms() {
		setBoardroomHasNameMock(boardroom, A_BOARDROOM_NAME);
		boardroomInMemoryRepository.persist(boardroom);
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
		boardroomInMemoryRepository.persist(boardroomWithSecondLeastNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithMostNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithThirdLeastNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithLeastNumberOfSeats);
	}

	private void setUpRepositoryWithOrderedListOfBoardrooms() {
		boardroomInMemoryRepository.persist(boardroomWithLeastNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithSecondLeastNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithThirdLeastNumberOfSeats);
		boardroomInMemoryRepository.persist(boardroomWithMostNumberOfSeats);
	}

	private Collection<Boardroom> orderedListOfBoardroomsByNumberOfSeats() {
		return Arrays.asList(boardroomWithLeastNumberOfSeats, boardroomWithSecondLeastNumberOfSeats, boardroomWithThirdLeastNumberOfSeats,
				boardroomWithMostNumberOfSeats);
	}
}