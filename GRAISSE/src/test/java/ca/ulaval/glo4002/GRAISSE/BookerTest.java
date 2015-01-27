package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BookerTest {

	private Booker booker;

	@Mock
	Booking bookingToAdd;

	@Before
	public void setup() {
		booker = new Booker();
	}

	@Test
	public void atCreationNumberOfJobsToDoIsZero() {
		assertEquals(0, booker.numberOfJobsToDo());
	}

	@Test
	public void atCreationHaveWorkToDoShouldReturnFalse() {
		assertFalse(booker.hasWorkToDO());
	}

	@Test
	public void afterAddingBookingNumberOfJobsToDoIsSuperiorToZero() {
		addBookingToBooker();
		assertTrue(0 < booker.numberOfJobsToDo());
	}
	
	@Test
	public void afterAddingBookinghaveWorkToDoShouldReturnTrue() {
		addBookingToBooker();
		assertTrue(booker.hasWorkToDO());
	}

	private void addBookingToBooker() {
		booker.addBooking(bookingToAdd);
	}

}
