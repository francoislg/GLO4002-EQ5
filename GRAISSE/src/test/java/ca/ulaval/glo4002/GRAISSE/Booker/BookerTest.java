package ca.ulaval.glo4002.GRAISSE.Booker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
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
import ca.ulaval.glo4002.GRAISSE.Trigger.Trigger;

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
	
	@Mock
	Trigger trigger;
	
	@Mock
	Trigger secondTrigger;

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
	public void whenBookingsIsEmptyTheBookerShouldNotHasBookingsToAssign() {
		doReturn(true).when(bookings).isEmpty();
		
		assertFalse(booker.hasBookingsToAssign());
	}
	
	
	@Test
	public void afterAddingABookingTheBookerShouldHasBookingsToAssign() {
		doReturn(false).when(bookings).isEmpty();
		
		assertTrue(booker.hasBookingsToAssign());
	}

	@Test
	public void onCreationTheBookerShouldHaveZeroJobsToDo() {
		assertEquals(0, booker.numberOfBookingsToAssign());
	}

	@Test
	public void setStrategyToBasicShouldUseTheFactoryToGetABookingStrategyBasicObject() {
		booker.setStrategyToBasic();
		verify(bookingStrategiesFactory, times(2)).createBasicStrategy();
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
	public void theBookerAfterRegistringTheSameTriggerTwiceOnlyUpdateTheTriggerOnceWhenAssigningBookings() {
		booker.registerTrigger(trigger);
		booker.registerTrigger(trigger);
		
		booker.assignBookings();
		
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
