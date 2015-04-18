package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class BookingIDTest {
	private final static String AN_ID = "AnID";
	private final static String A_DIFFERENT_ID = "ADifferentID";
	
	private BookingID bookingID;

	@Before
	public void setUp() {
		bookingID = new BookingID(AN_ID);
	}

	@Test
	public void toStringShouldReturnSameString() {
		assertEquals(AN_ID, bookingID.toString());
	}

	@Test
	public void givenAnotherObjectWithSameIDShouldBeEquals() {
		assertEquals(bookingID, new BookingID(AN_ID));
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
	public void givenANullObjectShouldBeDifferents() {
		assertFalse(bookingID.equals(null));
	}
	
	@Test
	public void anHashCodeIsAlwaysAPositiveNumber() {
		assertTrue(bookingID.hashCode() > 0);
	}
	
	
}
