package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {
	
	private static final boolean ASSIGNED = true;
	private static final boolean NOT_ASSIGNED = false;

	private Bookings bookings;
	
	private Collection<Booking> emptyBookingCollection;
	
	private Collection<Booking> bookingsWithOneUnassignedBookings;
	
	private Collection<Booking> bookingsWithOnlyAssignedBookings;

	@Mock
	Booking booking;
	
	@Mock
	Booking assignedBooking1;
	
	@Mock
	Booking assignedBooking2;
	
	@Mock
	Booking unassignedBooking;

	@Mock
	Boardrooms boardrooms;

	@Mock
	BookingsStrategy bookingsStrategy;

	@Mock
	BoardroomsStrategy boardroomsStrategy;
	
	@Mock
	BookingRepository bookingRepository;

	@Before
	public void setUp() {
		bookings = new Bookings(bookingRepository);
		
		emptyBookingCollection = new ArrayList<Booking>();
		bookingsWithOneUnassignedBookings = new ArrayList<Booking>();
		bookingsWithOnlyAssignedBookings = new ArrayList<Booking>();
		
		Mockito.doReturn(ASSIGNED).when(assignedBooking1).isAssigned();
		Mockito.doReturn(ASSIGNED).when(assignedBooking2).isAssigned();
		Mockito.doReturn(NOT_ASSIGNED).when(unassignedBooking).isAssigned();
		
		bookingsWithOneUnassignedBookings.add(assignedBooking1);
		bookingsWithOneUnassignedBookings.add(assignedBooking2);
		bookingsWithOneUnassignedBookings.add(unassignedBooking);
		
		bookingsWithOnlyAssignedBookings.add(assignedBooking1);
		bookingsWithOnlyAssignedBookings.add(assignedBooking2);
	}
	
	@Test
	public void whenAddingABookingBookingsShouldTellRepositoryToPersistIt() {
		bookings.add(booking);
		
		Mockito.verify(bookingRepository).persist(booking);
	}

	@Test
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryIsEmpty() {
		Mockito.doReturn(emptyBookingCollection).when(bookingRepository).retrieveAll();
		
		assertFalse(bookings.hasUnassignedBookings());
	}
	
	@Test
	public void bookingsShouldHaveUnassignedBookingsIfTheRepositoryContainOneUnassignedBooking() {
		Mockito.doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		
		assertTrue(bookings.hasUnassignedBookings());
	}
	
	@Test 
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryContainOnlyAssignedBooking() {
		Mockito.doReturn(bookingsWithOnlyAssignedBookings).when(bookingRepository).retrieveAll();
		
		assertFalse(bookings.hasUnassignedBookings());
	}
	
	@Test
	public void assigningBookingsShouldOnlyBeAttemptedWithUnassignedBookings() {
		Mockito.doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		Mockito.doReturn(bookingsWithOneUnassignedBookings).when(bookingsStrategy).sort(bookingsWithOneUnassignedBookings);
		
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
		
		Mockito.verify(boardrooms).assignBookingToBoardroom(unassignedBooking, boardroomsStrategy);
		Mockito.verify(boardrooms, Mockito.never()).assignBookingToBoardroom(assignedBooking1, boardroomsStrategy);
		Mockito.verify(boardrooms, Mockito.never()).assignBookingToBoardroom(assignedBooking2, boardroomsStrategy);
	}
	
	@Test
	public void newlyAssignedBookingsShouldBePersistedAfterTheAssignation() {
		Mockito.doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		Mockito.doReturn(bookingsWithOneUnassignedBookings).when(bookingsStrategy).sort(bookingsWithOneUnassignedBookings);
		
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
		
		Mockito.verify(bookingRepository).persist(unassignedBooking);
	}
}
