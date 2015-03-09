package ca.ulaval.glo4002.GRAISSE.Boardroom;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategyOptimize;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsStrategyOptimizeTest {
	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	BoardroomsStrategyOptimize boardroomsStrategyOptimize;

	@Mock
	private Boardrooms boardrooms;

	@Mock
	private Boardroom BoardroomSmallestNumberOfSeatsNeeded;

	@Mock
	private Boardroom BoardroomSecondSmallestNumberOfSeatsNeeded;

	@Mock
	private Boardroom BoardroomBiggestNumberOfSeatsNeeded;

	@Before
	public void setUp() {
		boardroomsStrategyOptimize = new BoardroomsStrategyOptimize();
		when(BoardroomSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroomNumberOfSeats(any())).thenReturn(SMALLER);
		when(BoardroomSecondSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroomNumberOfSeats(BoardroomSmallestNumberOfSeatsNeeded)).thenReturn(BIGGER);
		when(BoardroomSecondSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroomNumberOfSeats(BoardroomBiggestNumberOfSeatsNeeded)).thenReturn(SMALLER);
		when(BoardroomBiggestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroomNumberOfSeats(any())).thenReturn(BIGGER);
	}

	@Test
	public void withUnorderedListformatShouldAnOrderByNumberOfSeats() {
		Collection<Boardroom> boardroomList = new ArrayList<Boardroom>(Arrays.asList(BoardroomBiggestNumberOfSeatsNeeded, BoardroomSecondSmallestNumberOfSeatsNeeded,
				BoardroomSmallestNumberOfSeatsNeeded));
		Collection<Boardroom> expectedBoardroomList = new ArrayList<Boardroom>(Arrays.asList(BoardroomSmallestNumberOfSeatsNeeded,
				BoardroomSecondSmallestNumberOfSeatsNeeded, BoardroomBiggestNumberOfSeatsNeeded));
		Collection<Boardroom> result = boardroomsStrategyOptimize.sort(boardroomList);
		assertEquals(expectedBoardroomList, result);
	}
}
