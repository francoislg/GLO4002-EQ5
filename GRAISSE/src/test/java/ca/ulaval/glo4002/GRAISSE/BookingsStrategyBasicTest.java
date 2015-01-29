package ca.ulaval.glo4002.GRAISSE;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingsStrategyBasicTest {

	BookingsStrategyBasic bookingsStrategyBasic;

	@Mock
	Collection<Booking> bookingCollection;

	@Before
	public void setUp() {
		bookingsStrategyBasic = new BookingsStrategyBasic();
	}

	@Test
	public void withBookingCollectionformatShouldReturnTheSameBoardroomCollection() {
		assertEquals(bookingCollection, bookingsStrategyBasic.format(bookingCollection));
	}

}
