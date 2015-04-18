package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BookingIDTest {
	private final static String A_RANDOM_ID = "RandomID";

	private BookingID bookingID;

	@Before
	public void setUp() {
		bookingID = new BookingID(A_RANDOM_ID);
	}

	@Test
	public void toStringShouldReturnSameString() {
		assertEquals(A_RANDOM_ID, bookingID.toString());
	}

	@Test
	public void givenAnotherObjectWithSameIDShouldBeEquals() {
		assertEquals(bookingID, new BookingID(A_RANDOM_ID));
	}
}
