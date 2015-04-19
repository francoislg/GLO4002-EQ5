package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookingsSortingStrategyDefaultTest {

	@Mock
	private BookingRepository bookingRepository;

	@Mock
	private Booking booking;

	private Collection<Booking> bookingsList = new ArrayList<Booking>();
	private BookingsSortingStrategyDefault bookingSorting;

	@Before
	public void setUp() {
		when(bookingRepository.retrieveAll()).thenReturn(bookingsList);
		bookingSorting = new BookingsSortingStrategyDefault();
	}

	@Test
	public void sortShouldCallretrieveAllOnTheBookingRepository() {
		bookingSorting.sort(bookingRepository);

		verify(bookingRepository).retrieveAll();
	}

	@Test
	public void sortShouldReturnAllBookingsFromRepository() {
		fillBookingList();

		Collection<Booking> result = bookingSorting.sort(bookingRepository);

		assertArrayEquals(bookingsList.toArray(), result.toArray());
	}

	private void fillBookingList() {
		bookingsList.add(booking);
	}
}
