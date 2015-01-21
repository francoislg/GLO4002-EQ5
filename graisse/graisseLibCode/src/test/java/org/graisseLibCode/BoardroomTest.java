package org.graisseLibCode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardroomTest {

	private static final int NUMBEROFSEATSINBOARDROOM = 10;
	private static final int NUMBEROFSEATSBIGGERTHANAVAILABLE = 11;
	private static final int NUMBEROFSEATSSMALLERTHANAVALAIBLE = 9;

	Boardroom boardroom;

	@Before
	public void setUp() {
		boardroom = new Boardroom(NUMBEROFSEATSINBOARDROOM);
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
