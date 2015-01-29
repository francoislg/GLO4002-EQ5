package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomsStrategyBasicTest {

	BoardroomsStrategyBasic boardroomsStrategyBasic;

	@Mock
	Collection<Boardroom> boardroomCollection;

	@Before
	public void setUp() {
		boardroomsStrategyBasic = new BoardroomsStrategyBasic();
	}

	@Test
	public void withBoardroomCollectionformatShouldReturnTheSameBoardroomCollection() {
		assertEquals(boardroomCollection, boardroomsStrategyBasic.format(boardroomCollection));
	}

}
