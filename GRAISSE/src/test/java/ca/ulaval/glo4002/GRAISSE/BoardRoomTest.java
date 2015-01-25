package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardroomTest {

	private static final String NAMEOFBOARDROOM1 = "Boardroom1";

	private static final String NAMENOTEQUALTONAMEOFBOARDROOM1 = "Boardroom1Different";

	private static final int NUMBEROFSEATSINBOARDROOM = 10;
	private static final int NUMBEROFSEATSBIGGERTHANAVAILABLE = 11;
	private static final int NUMBEROFSEATSSMALLERTHANAVALAIBLE = 9;

	Boardroom boardroom;

	@Mock
	Booking booking;

	@Before
	public void setUp() {
		boardroom = new Boardroom(NAMEOFBOARDROOM1, NUMBEROFSEATSINBOARDROOM);
	}

	@Test
	public void IfNotEnoughtSeatsVerifyNumberOfSeatsReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM)).thenReturn(
				false);
		assertFalse(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfEnoughtSeatsVerifyNumberOfSeatsReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM)).thenReturn(
				true);
		assertTrue(boardroom.verifyNumberOfSeats(booking));
	}

	@Test
	public void IfSameNameIsMyNameReturnTrue() {
		assertTrue(boardroom.isMyName(NAMEOFBOARDROOM1));
	}

	@Test
	public void IfNotSameNameIsMyNameReturnFalse() {
		assertFalse(boardroom.isMyName(NAMENOTEQUALTONAMEOFBOARDROOM1));
	}

	@Test
	public void firstAssignShouldReturnTrue() {
		when(booking.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM)).thenReturn(
				true);
		assertTrue(boardroom.assign(booking));
	}

	@Test
	public void secondAssignShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM)).thenReturn(
				true);
		boardroom.assign(booking);
		assertFalse(boardroom.assign(booking));
	}

	@Test
	public void IfVerifyFailassignToBoardroomShouldReturnFalse() {
		when(booking.verifyNumberOfSeats(NUMBEROFSEATSINBOARDROOM)).thenReturn(
				false);
		assertFalse(boardroom.assign(booking));
	}

}
