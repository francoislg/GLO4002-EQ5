package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.BookingsSortingStrategyDefault;

@RunWith(MockitoJUnitRunner.class)
public class BookingsSortingStrategyDefaultTest {

	@Mock
	Collection<Booking> bookingCollection;

	BookingsSortingStrategyDefault bookingsSortingStrategyDefault;
	
	@Before
	public void setUp() {
		bookingsSortingStrategyDefault = new BookingsSortingStrategyDefault();
	}

	@Test
	public void withBookingCollectionSortShouldReturnTheSameBoardroomCollection() {
		assertEquals(bookingCollection, bookingsSortingStrategyDefault.sort(bookingCollection));
	}
}