package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardRoomTest {

	private static final int NUMBEROFSEATSINBOARDROOM = 10;
	private static final int NUMBEROFSEATSBIGGERTHANAVAILABLE = 11;
	private static final int NUMBEROFSEATSSMALLERTHANAVALAIBLE = 9;

	BoardRoom boardroom;

	@Before
	public void setUp() {
		boardroom = new BoardRoom(NUMBEROFSEATSINBOARDROOM);
	}

	@Test
	public void verifyNumberOfSeatsReturnFalseIfNotEnoughtSeats() {
		assertFalse(boardroom
				.verifyNumberOfSeats(NUMBEROFSEATSBIGGERTHANAVAILABLE));
	}

	@Test
	public void verifyNumberOfSeatsReturnTrueIfEnoughtSeats() {
		assertTrue(boardroom
				.verifyNumberOfSeats(NUMBEROFSEATSSMALLERTHANAVALAIBLE));
		assertTrue(boardroom.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM));
	}

}
