package ca.ulaval.glo4002.GRAISSE.boardroom;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategyDefault;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsSortingStrategyDefaultTest {

	BoardroomsSortingStrategyDefault boardroomsSortingStrategyDefault;

	@Mock
	Collection<Boardroom> boardroomCollection;

	@Before
	public void setUp() {
		boardroomsSortingStrategyDefault = new BoardroomsSortingStrategyDefault();
	}

	@Test
	public void givenAListOfBoardroomWhenSortingWithStrategyShouldReturnSameList() {
		assertEquals(boardroomCollection, boardroomsSortingStrategyDefault.sort(boardroomCollection));
	}
}
