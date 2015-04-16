package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BookingDTOTest {
	private static final String PROMOTER_EMAIL = null;
	private static final BookingState A_STATE = BookingState.WAITING;
	private static final String BOARDROOM_NAME = null;
	private static final int NUMBER_OF_SEATS = 1;

	@Mock
	BookingID bookingID;

	private BookingDTO bookingDTO;

	@Before
	public void setUp() {
		bookingDTO = new BookingDTO(bookingID, NUMBER_OF_SEATS, PROMOTER_EMAIL, A_STATE, BOARDROOM_NAME);
	}

	@Test
	public void idShouldBeTheSameAsGiven() {
		assertEquals(bookingDTO.getID(), bookingID);
	}

	@Test
	public void numberOfSeatsShouldBeTheSameAsGiven() {
		assertEquals(bookingDTO.getNumberOfSeats(), NUMBER_OF_SEATS);
	}

	@Test
	public void stateShouldBeTheSameAsGiven() {
		assertEquals(bookingDTO.getBookingState(), A_STATE);
	}

	@Test
	public void boardroomNameShouldBeTheSameAsGiven() {
		assertEquals(bookingDTO.getBoardroomName(), BOARDROOM_NAME);
	}
}
