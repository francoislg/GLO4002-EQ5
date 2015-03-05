package ca.ulaval.glo4002.GRAISSE.Booker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.Boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.Booking.Booking;
import ca.ulaval.glo4002.GRAISSE.Booking.Bookings;

@RunWith(MockitoJUnitRunner.class)
public class BookerTest {

	Booker booker;

	@Mock
	Bookings bookings;

	@Mock
	BookerStrategy bookingStrategy;

	@Mock
	BookerStrategiesFactory bookingStrategiesFactory;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Booking booking;

	@Before
	public void setUp() {
		when(bookingStrategiesFactory.createBasicStrategy()).thenReturn(bookingStrategy);
		booker = new Booker(bookingStrategiesFactory, bookings, boardrooms);
	}

	@Test
	public void assignBookingShouldcallassignBookingsOnbookingStrategy() {
		booker.assignBookings();
		verify(bookingStrategy, times(1)).assignBookings(boardrooms, bookings);
	}

	@Test
	public void addBookingShouldaddABoookingToBookings() {
		booker.addBooking(booking);
		verify(bookings, times(1)).addBooking(booking);
	}

	@Test
	public void onCreationTheBookerShouldBeSetWithABookingStrategyBasic() {
		verify(bookingStrategiesFactory, times(1)).createBasicStrategy();
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
		verify(bookingStrategiesFactory, times(2)).createBasicStrategy();
	}

	@Test
	public void theBookerShouldCallNotifyObserversThatBookerHasChangedAfterAssigningBookings() {
		booker.assignBookings();
		verify(booker, times(1)).notifyObserversThatBookerHasChanged();
	}

	@Test
	public void theBookerShouldCallNotifyObserversThatBookerHasChangedAfterAddingANewBooking() {
		booker.addBooking(booking);
		verify(booker, times(1)).notifyObserversThatBookerHasChanged();
	}

	@Test
	public void theBookerShouldNotifyObserversAfterAssigningBookings() {
		booker.assignBookings();
		verify(booker, times(1)).notifyObservers();
	}

}
