package ca.ulaval.glo4002.GRAISSE.boardroom;

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
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyBySeats;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsSortingStrategyBySeatsTest {
	
	private static final int BIGGER = 1;
	private static final int SMALLER = -1;
	private static final int EQUAL = 0;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Boardroom boardroomSmallestNumberOfSeatsNeeded;

	@Mock
	Boardroom boardroomSecondSmallestNumberOfSeatsNeeded;
	
	@Mock
	Boardroom boardroomSecondSmallestNumberOfSeatsNeeded2;

	@Mock
	Boardroom boardroomBiggestNumberOfSeatsNeeded;
	
	BoardroomsSortingStrategyBySeats boardroomsSortingStrategyBySeats;

	@Before
	public void setUp() {
		boardroomsSortingStrategyBySeats = new BoardroomsSortingStrategyBySeats();
		
		when(boardroomSmallestNumberOfSeatsNeeded.compareByNumberOfSeats(any())).thenReturn(SMALLER);
		
		when(boardroomSecondSmallestNumberOfSeatsNeeded.compareByNumberOfSeats(boardroomSmallestNumberOfSeatsNeeded)).thenReturn(BIGGER);
		when(boardroomSecondSmallestNumberOfSeatsNeeded.compareByNumberOfSeats(boardroomBiggestNumberOfSeatsNeeded)).thenReturn(SMALLER);
		when(boardroomSecondSmallestNumberOfSeatsNeeded.compareByNumberOfSeats(boardroomSecondSmallestNumberOfSeatsNeeded2)).thenReturn(EQUAL);
		
		when(boardroomSecondSmallestNumberOfSeatsNeeded2.compareByNumberOfSeats(boardroomSmallestNumberOfSeatsNeeded)).thenReturn(BIGGER);
		when(boardroomSecondSmallestNumberOfSeatsNeeded2.compareByNumberOfSeats(boardroomBiggestNumberOfSeatsNeeded)).thenReturn(SMALLER);
		when(boardroomSecondSmallestNumberOfSeatsNeeded2.compareByNumberOfSeats(boardroomSecondSmallestNumberOfSeatsNeeded)).thenReturn(EQUAL);
		
		when(boardroomBiggestNumberOfSeatsNeeded.compareByNumberOfSeats(any())).thenReturn(BIGGER);
	}

	@Test
	public void withUnorderedListTheStrategyShouldOrderTheListByNumberOfSeats() {
		Collection<Boardroom> boardroomList = new ArrayList<Boardroom>(Arrays.asList(boardroomSecondSmallestNumberOfSeatsNeeded, 
				boardroomBiggestNumberOfSeatsNeeded, boardroomSecondSmallestNumberOfSeatsNeeded2, boardroomSmallestNumberOfSeatsNeeded));
		
		Collection<Boardroom> expectedBoardroomList = new ArrayList<Boardroom>(Arrays.asList(boardroomSmallestNumberOfSeatsNeeded,
				boardroomSecondSmallestNumberOfSeatsNeeded, boardroomSecondSmallestNumberOfSeatsNeeded2, boardroomBiggestNumberOfSeatsNeeded));
		
		Collection<Boardroom> result = boardroomsSortingStrategyBySeats.sort(boardroomList);
		assertEquals(expectedBoardroomList, result);
	}
}
