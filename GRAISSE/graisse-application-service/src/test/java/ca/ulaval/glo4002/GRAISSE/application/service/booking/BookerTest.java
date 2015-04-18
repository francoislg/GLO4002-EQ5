package ca.ulaval.glo4002.GRAISSE.application.service.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Observer;

@RunWith(MockitoJUnitRunner.class)
public class BookerTest {

	@Mock
	Bookings bookings;

	@Mock
	Reservations reservations;

	@Mock
	BookerStrategy bookerStrategy;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Booking booking;

	@Mock
	Observer<Booker> observer;

	@Mock
	Observer<Booker> secondObserver;

	Booker booker;

	@Before
	public void setUp() {
		booker = new Booker(bookings, boardrooms, reservations);
	}

	@Test
	public void whenSettingNewStrategyassignBookingsShouldCallAssignBookingsOnBookerStrategy() {
		booker.setBookerStrategy(bookerStrategy);
		booker.assignBookings();
		verify(bookerStrategy).assignBookings(reservations);
	}

	@Test
	public void addBookingShouldAddABookingToBookings() {
		booker.addBooking(booking);
		verify(bookings).add(booking);
	}

	@Test
	public void whenBookingsHasNoBookingToAssignBookerShouldNotHaveBookingsToAssign() {
		doReturn(false).when(bookings).hasAssignableBookings();
		assertFalse(booker.hasBookingsToAssign());
	}

	@Test
	public void whenBookingsHasBookingToAssignBookerShouldHaveBookingsToAssign() {
		doReturn(true).when(bookings).hasAssignableBookings();
		assertTrue(booker.hasBookingsToAssign());
	}

	@Test
	public void givenTwoTriggersRegisteredInBookerWhenAddingANewBookingBookerShouldUpdateBothTriggers() {
		booker.registerTrigger(observer);
		booker.registerTrigger(secondObserver);

		booker.addBooking(booking);

		verify(observer).update(booker);
		verify(secondObserver).update(booker);
	}

	@Test
	public void givenTwoOfTheSameTriggerRegisteredInBookerWhenAddingABookingShouldUpdateTriggerOnlyOnce() {
		booker.registerTrigger(observer);
		booker.registerTrigger(observer);

		booker.addBooking(booking);

		verify(observer).update(booker);
	}

	@Test
	public void givenABookingToAssignAndTwoTriggersRegisteredInBookerWhenAssigningBookerShouldUpdateBothTriggers() {
		booker.addBooking(booking);
		booker.registerTrigger(observer);
		booker.registerTrigger(secondObserver);

		booker.assignBookings();

		verify(observer).update(booker);
		verify(secondObserver).update(booker);
	}
}
