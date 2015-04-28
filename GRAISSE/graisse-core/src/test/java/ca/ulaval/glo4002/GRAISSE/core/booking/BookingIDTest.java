package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BookingIDTest {
	private final static String A_ID = "AID";
	private final static String A_DIFFERENT_ID = "ADifferentID";

	private BookingID bookingID;

	@Before
	public void setUp() {
		bookingID = new BookingID(A_ID);
	}

	@Test
	public void toStringShouldReturnSameString() {
		assertEquals(A_ID, bookingID.toString());
	}

	@Test
	public void givenAnotherObjectWithSameIDShouldBeEquals() {
		assertEquals(bookingID, new BookingID(A_ID));
	}

	@Test
	public void givenSameObjectShouldBeEquals() {
		assertEquals(bookingID, bookingID);
	}

	@Test
	public void givenAnObjectWithdifferentIDShouldBeDifferents() {
		assertThat(bookingID, is(not(new BookingID(A_DIFFERENT_ID))));
	}

	@Test
	public void givenANullObjectEqualsShouldReturnFalse() {
		assertFalse(bookingID.equals(null));
	}

}
