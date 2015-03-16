package ca.ulaval.glo4002.GRAISSE.booker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.booking.Bookings;

@RunWith(MockitoJUnitRunner.class)
public class BookerTest {

	@Mock
	Bookings bookings;

	@Mock
	BookerStrategy bookerStrategy;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Booking booking;
	
	@Mock
	BookerTrigger trigger;
	
	@Mock
	BookerTrigger secondTrigger;
	
	Booker booker;

	@Before
	public void setUp() {
		booker = new Booker(bookerStrategy, bookings, boardrooms);
	}

	@Test
	public void assignBookingsShouldCallAssignBookingsOnBookerStrategy() {
		booker.assignBookings();
		verify(bookerStrategy).assignBookings(boardrooms, bookings);
	}

	@Test
	public void addBookingShouldAddABookingToBookings() {
		booker.addBooking(booking);
		verify(bookings).add(booking);
	}

	@Test
	public void whenBookingsHasNoBookingToAssignBookerShouldNotHaveBookingsToAssign() {
		doReturn(false).when(bookings).hasUnassignedBookings();
		assertFalse(booker.hasBookingsToAssign());
	}
	
	
	@Test
	public void whenBookingsHasBookingToAssignBookerShouldHaveBookingsToAssign() {
		doReturn(true).when(bookings).hasUnassignedBookings();
		assertTrue(booker.hasBookingsToAssign());
	}

	@Test
	public void givenTwoTriggersRegisteredInBookerWhenAddingANewBookingBookerShouldUpdateBothTriggers() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(secondTrigger);
		
		booker.addBooking(booking);
		
		verify(trigger).update(booker);
		verify(secondTrigger).update(booker);
	}
	
	@Test
	public void givenTwoOfTheSameTriggerRegisteredInBookerWhenAddingABookingShouldUpdateTriggerOnlyOnce() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(trigger);
		
		booker.addBooking(booking);
		
		verify(trigger).update(booker);
	}
	
	@Test
	public void givenABookingToAssignAndTwoTriggersRegisteredInBookerWhenAssigningBookerShouldUpdateBothTriggers() {
		booker.addBooking(booking);
		booker.registerTrigger(trigger);
		booker.registerTrigger(secondTrigger);
		
		booker.assignBookings();
		
		verify(trigger).update(booker);
		verify(secondTrigger).update(booker);
	}
}
