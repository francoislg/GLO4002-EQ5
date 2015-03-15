package ca.ulaval.glo4002.GRAISSE.booker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
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

	Booker booker;

	@Mock
	Bookings bookings;

	@Mock
	BookerStrategy bookerStrategy;

	@Mock
	Boardrooms boardrooms;

	@Mock
	Booking booking;
	
	@Mock
	Trigger trigger;
	
	@Mock
	Trigger secondTrigger;

	@Before
	public void setUp() {
		booker = new Booker(bookerStrategy, bookings, boardrooms);
	}

	@Test
	public void assignBookingShouldCallAssignBookingsOnBookingStrategy() {
		booker.assignBookings();
		verify(bookerStrategy, times(1)).assignBookings(boardrooms, bookings);
	}

	@Test
	public void addBookingShouldAddABookingToBookings() {
		booker.addBooking(booking);
		verify(bookings, times(1)).add(booking);
	}

	@Test
	public void whenBookingsHasNoBookingToAssignedTheBookerShouldNotHaveBookingsToAssign() {
		doReturn(false).when(bookings).hasUnassignedBookings();
		
		assertFalse(booker.hasBookingsToAssign());
	}
	
	
	@Test
	public void whenBookingsHasBookingToAssignedTheBookerShouldHaveBookingsToAssign() {
		doReturn(true).when(bookings).hasUnassignedBookings();
		
		assertTrue(booker.hasBookingsToAssign());
	}

	@Test
	public void onCreationTheBookerShouldHaveZeroJobsToDo() {
		assertEquals(0, booker.numberOfBookingsToAssign());
	}

	@Test
	public void theBookerAfterAddingANewBookingShouldUpdateRegistredTriggers() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(secondTrigger);
		
		booker.addBooking(booking);
		
		verify(trigger).update(booker);
		verify(secondTrigger).update(booker);
	}
	
	@Test
	public void theBookerAfterRegistringTheSameTriggerTwiceOnlyUpdateTheTriggerOnceWhenAddingBooking() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(trigger);
		
		booker.addBooking(booking);
		
		verify(trigger).update(booker);
	}
	
	@Test
	public void theBookerWithBookingToAssignShouldUpdateRegistredTriggersWhenAssigningBookings() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(secondTrigger);
		
		booker.addBooking(booking);
		booker.assignBookings();
		
		verify(trigger, times(2)).update(booker);
		verify(secondTrigger, times(2)).update(booker);
	}
}
