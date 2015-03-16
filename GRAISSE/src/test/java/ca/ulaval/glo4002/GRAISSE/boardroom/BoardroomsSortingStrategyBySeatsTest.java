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
	Boardroom boardroomWithLeastNumberOfSeats;

	@Mock
	Boardroom boardroomWithSecondLeastNumberOfSeats;
	
	@Mock
	Boardroom boardroomWithThirdLeastNumberOfSeats;

	@Mock
	Boardroom boardroomWithMostNumberOfSeats;
	
	BoardroomsSortingStrategyBySeats boardroomsSortingStrategyBySeats;

	@Before
	public void setUp() {
		setUpMocksForMultipleBoardrooms();
		
		boardroomsSortingStrategyBySeats = new BoardroomsSortingStrategyBySeats();
	}

	@Test
	public void withUnorderedListTheStrategyShouldOrderTheListByNumberOfSeats() {
		Collection<Boardroom> unorderedBoardroomList = unorderedListOfBoardrooms();
		Collection<Boardroom> expectedBoardroomList = orderedListOfBoardroomsByNumberOfSeats();
		
		Collection<Boardroom> result = boardroomsSortingStrategyBySeats.sort(unorderedBoardroomList);
		
		assertEquals(expectedBoardroomList, result);
	}
	
	private void setUpMocksForMultipleBoardrooms(){
		when(boardroomWithLeastNumberOfSeats.compareByNumberOfSeats(any())).thenReturn(SMALLER);
		
		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithLeastNumberOfSeats)).thenReturn(BIGGER);
		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithMostNumberOfSeats)).thenReturn(SMALLER);
		when(boardroomWithSecondLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithThirdLeastNumberOfSeats)).thenReturn(EQUAL);
		
		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithLeastNumberOfSeats)).thenReturn(BIGGER);
		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithMostNumberOfSeats)).thenReturn(SMALLER);
		when(boardroomWithThirdLeastNumberOfSeats.compareByNumberOfSeats(boardroomWithSecondLeastNumberOfSeats)).thenReturn(EQUAL);
	
		when(boardroomWithMostNumberOfSeats.compareByNumberOfSeats(any())).thenReturn(BIGGER);
	}
	
	private Collection<Boardroom> unorderedListOfBoardrooms(){
		return Arrays.asList(boardroomWithSecondLeastNumberOfSeats, boardroomWithMostNumberOfSeats, boardroomWithThirdLeastNumberOfSeats, boardroomWithLeastNumberOfSeats);
	}
	
	private Collection<Boardroom> orderedListOfBoardroomsByNumberOfSeats(){
		return Arrays.asList(boardroomWithLeastNumberOfSeats, boardroomWithSecondLeastNumberOfSeats, boardroomWithThirdLeastNumberOfSeats, boardroomWithMostNumberOfSeats);
	}
}
