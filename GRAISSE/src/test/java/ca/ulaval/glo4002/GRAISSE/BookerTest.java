package ca.ulaval.glo4002.GRAISSE;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BookerTest extends TestCase {

	Booker booker;

	@Mock
	Bookings bookings;

	@Mock
	BookingStrategy bookingStrategy;

	@Mock
	BookingStrategiesFactory bookingStrategiesFactory;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Booking booking;

	@Before
	public void setUp() {
		when(bookingStrategiesFactory.createBasicStrategy(boardrooms)).thenReturn(bookingStrategy);
		booker = new Booker(bookingStrategiesFactory, bookings, boardrooms);
	}

	@Test
	public void assignBookingShouldcallassignBookingsOnbookingStrategy() {
		booker.assignBookings();
		verify(bookingStrategy, times(1)).assignBookings(bookings);
	}

	@Test
	public void addBookingShouldaddABoookingToBookings() {
		booker.addBooking(booking);
		verify(bookings, times(1)).addBooking(booking);
	}

	@Test
	public void onCreationTheBookerShouldBeSetWithABookingStrategyBasic() {
		verify(bookingStrategiesFactory, times(1)).createBasicStrategy(boardrooms);
	}

	@Test
	public void onCreationTheBookerShouldNotHasWorkToDo() {
		assertFalse(booker.hasWorkToDO());
	}

	@Test
	public void onCreationTheBookerShouldHaveZeroJobsToDo() {
		assertEquals(0, booker.numberOfJobsToDo());
	}

	@Test
	public void setStrategyToBasicShouldUseTheFactoryToGetABookingStrategyBasicObject() {
		booker.setStrategyToBasic();
		verify(bookingStrategiesFactory, times(2)).createBasicStrategy(boardrooms);
	}
}