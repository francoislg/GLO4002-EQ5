package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
		
		doReturn(ASSIGNED).when(assignedBooking1).isAssigned();
		doReturn(ASSIGNED).when(assignedBooking2).isAssigned();
		doReturn(NOT_ASSIGNED).when(unassignedBooking).isAssigned();
		
		bookingsWithOneUnassignedBookings.add(assignedBooking1);
		bookingsWithOneUnassignedBookings.add(assignedBooking2);
		bookingsWithOneUnassignedBookings.add(unassignedBooking);
		
		bookingsWithOnlyAssignedBookings.add(assignedBooking1);
		bookingsWithOnlyAssignedBookings.add(assignedBooking2);
	}
	
	@Test
	public void whenAddingABookingBookingsShouldTellRepositoryToPersistIt() {
		bookings.add(booking);
		
		verify(bookingRepository).persist(booking);
	}

	@Test
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryIsEmpty() {
		doReturn(emptyBookingCollection).when(bookingRepository).retrieveAll();
		
		assertFalse(bookings.hasUnassignedBookings());
	}
	
	@Test
	public void bookingsShouldHaveUnassignedBookingsIfTheRepositoryContainOneUnassignedBooking() {
		doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		
		assertTrue(bookings.hasUnassignedBookings());
	}
	
	@Test 
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryContainOnlyAssignedBooking() {
		doReturn(bookingsWithOnlyAssignedBookings).when(bookingRepository).retrieveAll();
		
		assertFalse(bookings.hasUnassignedBookings());
	}
	
	@Test
	public void assigningBookingsShouldOnlyBeAttemptedWithUnassignedBookings() {
		doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		doReturn(bookingsWithOneUnassignedBookings).when(bookingsStrategy).sort(bookingsWithOneUnassignedBookings);
		
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
		
		verify(boardrooms).assignBookingToBoardroom(unassignedBooking, boardroomsStrategy);
		verify(boardrooms, never()).assignBookingToBoardroom(assignedBooking1, boardroomsStrategy);
		verify(boardrooms, never()).assignBookingToBoardroom(assignedBooking2, boardroomsStrategy);
	}
	
	@Test
	public void newlyAssignedBookingsShouldBePersistedAfterTheAssignation() {
		doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		doReturn(bookingsWithOneUnassignedBookings).when(bookingsStrategy).sort(bookingsWithOneUnassignedBookings);
		
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsStrategy);
		
		verify(bookingRepository).persist(unassignedBooking);
	}
}
