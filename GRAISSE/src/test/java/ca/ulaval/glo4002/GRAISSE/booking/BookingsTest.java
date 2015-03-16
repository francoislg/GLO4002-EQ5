package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.boardroom.BoardroomsSortingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	private static final boolean ASSIGNED = true;
	private static final boolean NOT_ASSIGNED = false;

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
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	@Mock
	BookingRepository bookingRepository;
	
	Bookings bookings;
	Collection<Booking> emptyBookingCollection;
	Collection<Booking> bookingsWithOneUnassignedBookings;
	Collection<Booking> bookingsWithOnlyAssignedBookings;

	@Before
	public void setUp() {
		setUpBookingStrategyMock();
		setUpBookings();
		bookings = new Bookings(bookingRepository);
	}

	@Test
	public void whenAddingABookingBookingsShouldTellRepositoryToPersistIt() {
		bookings.add(booking);
		verify(bookingRepository).persist(booking);
	}

	@Test
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryIsEmpty() {
		setUpEmptyBookings();	
		assertFalse(bookings.hasUnassignedBookings());
	}

	@Test
	public void bookingsShouldHaveUnassignedBookingsIfTheRepositoryContainOneUnassignedBooking() {
		setUpOneUnassignedBookingInBookings();
		assertTrue(bookings.hasUnassignedBookings());
	}

	@Test
	public void bookingsShouldNotHaveUnassignedBookingsIfTheRepositoryContainOnlyAssignedBooking() {
		setUpAllAssignedBookings();
		assertFalse(bookings.hasUnassignedBookings());
	}

	@Test
	public void assigningBookingsShouldOnlyBeAttemptedWithUnassignedBookings() {
		setUpOneUnassignedBookingInBookings();
	
		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsSortingStrategy);

		verify(boardrooms).assignBookingToBoardroom(unassignedBooking, boardroomsSortingStrategy);
		verify(boardrooms, never()).assignBookingToBoardroom(assignedBooking1, boardroomsSortingStrategy);
		verify(boardrooms, never()).assignBookingToBoardroom(assignedBooking2, boardroomsSortingStrategy);
	}

	@Test
	public void newlyAssignedBookingsShouldBePersistedAfterTheAssignation() {
		setUpOneUnassignedBookingInBookings();

		bookings.assignBookingsToBoardrooms(boardrooms, bookingsStrategy, boardroomsSortingStrategy);

		verify(bookingRepository).persist(unassignedBooking);
	}

	private void setUpBookingStrategyMock() {
		List<Booking> formatedList = new ArrayList<Booking>(Arrays.asList(booking));
		when(bookingsStrategy.sort(any())).thenReturn(formatedList);
	}
	
	private void setUpBookings() {
		doReturn(ASSIGNED).when(assignedBooking1).isAssigned();
		doReturn(ASSIGNED).when(assignedBooking2).isAssigned();
		doReturn(NOT_ASSIGNED).when(unassignedBooking).isAssigned();
	}
	
	private void setUpEmptyBookings() {
		emptyBookingCollection = new ArrayList<Booking>();
		doReturn(emptyBookingCollection).when(bookingRepository).retrieveAll();
	}
	
	private void setUpAllAssignedBookings() {
		bookingsWithOnlyAssignedBookings = new ArrayList<Booking>();
		
		bookingsWithOnlyAssignedBookings.add(assignedBooking1);
		bookingsWithOnlyAssignedBookings.add(assignedBooking2);
		
		doReturn(bookingsWithOnlyAssignedBookings).when(bookingRepository).retrieveAll();
	}
	
	private void setUpOneUnassignedBookingInBookings() {
		bookingsWithOneUnassignedBookings = new ArrayList<Booking>();
	
		bookingsWithOneUnassignedBookings.add(assignedBooking1);
		bookingsWithOneUnassignedBookings.add(assignedBooking2);
		bookingsWithOneUnassignedBookings.add(unassignedBooking);
		
		doReturn(bookingsWithOneUnassignedBookings).when(bookingRepository).retrieveAll();
		doReturn(bookingsWithOneUnassignedBookings).when(bookingsStrategy).sort(bookingsWithOneUnassignedBookings);
	}
}