package ca.ulaval.glo4002.GRAISSE;

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

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsStrategyMaximiseTest {
	private static final int BIGGER = 1;
	private static final int SMALLER = -1;

	BoardroomsStrategyMaximise BoardroomsStrategyMaximise;

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
		BoardroomsStrategyMaximise = new BoardroomsStrategyMaximise();
		when(BoardroomSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroom(any())).thenReturn(SMALLER);
		when(BoardroomSecondSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroom(BoardroomSmallestNumberOfSeatsNeeded)).thenReturn(BIGGER);
		when(BoardroomSecondSmallestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroom(BoardroomBiggestNumberOfSeatsNeeded)).thenReturn(SMALLER);
		when(BoardroomBiggestNumberOfSeatsNeeded.compareNumberOfSeatsToBoardroom(any())).thenReturn(BIGGER);
	}

	@Test
	public void withUnorderedListformatShouldAnOrderByNumberOfSeats() {
		Collection<Boardroom> BoardroomList = new ArrayList<Boardroom>(Arrays.asList(BoardroomBiggestNumberOfSeatsNeeded, BoardroomSecondSmallestNumberOfSeatsNeeded,
				BoardroomSmallestNumberOfSeatsNeeded));
		Collection<Boardroom> expectedBoardroomList = new ArrayList<Boardroom>(Arrays.asList(BoardroomSmallestNumberOfSeatsNeeded,
				BoardroomSecondSmallestNumberOfSeatsNeeded, BoardroomBiggestNumberOfSeatsNeeded));
		Collection<Boardroom> result = BoardroomsStrategyMaximise.format(BoardroomList);
		assertEquals(expectedBoardroomList, result);
	}
}
