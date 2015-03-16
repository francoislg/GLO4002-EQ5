package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsStrategyBasic;

@RunWith(MockitoJUnitRunner.class)
public class BookingsStrategyBasicTest {

	@Mock
	Collection<Booking> bookingCollection;

	BookingsStrategyBasic bookingsStrategyBasic;
	
	@Before
	public void setUp() {
		bookingsStrategyBasic = new BookingsStrategyBasic();
	}

	@Test
	public void withBookingCollectionSortShouldReturnTheSameBoardroomCollection() {
		assertEquals(bookingCollection, bookingsStrategyBasic.sort(bookingCollection));
	}
}