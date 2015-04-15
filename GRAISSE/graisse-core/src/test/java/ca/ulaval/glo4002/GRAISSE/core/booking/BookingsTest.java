package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
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

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	private static final boolean ASSIGNABLE = true;
	private static final boolean NOT_ASSIGNABLE = false;

	@Mock
	BookingRepository bookingRepository;

	@Mock
	InterfaceReservationBooking interfaceReservationBooking;

	@Mock
	Booking booking;

	@Mock
	Booking unassignableBooking1;

	@Mock
	Booking unassignableBooking2;

	@Mock
	Booking assignableBooking;

	@Mock
	Boardrooms boardrooms;

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	Bookings bookings;
	Collection<Booking> emptyBookingCollection;
	Collection<Booking> bookingsWithOneAssignablBookings;
	Collection<Booking> bookingsNoAssignabledBooking;

	@Before
	public void setUp() {
		setUpBookingStrategyMock();
		bookings = new Bookings(bookingRepository, interfaceReservationBooking);
	}

	@Test
	public void whenAddingABookingBookingsShouldTellRepositoryToPersistIt() {
		bookings.add(booking);
		verify(bookingRepository).persist(booking);
	}

	@Test
	public void givenAnEmptyRepositoryBookingsShouldNotHaveUnassignedBookings() {
		setUpEmptyBookings();
		assertFalse(bookings.hasAssignableBookings());
	}

	@Test
	public void givenARepositoryWithOneUnassignedBookingBookingsShouldHaveUnassignedBookings() {
		setUpOneAssignableBookingInBookings();
		assertTrue(bookings.hasAssignableBookings());
	}

	@Test
	public void givenOneAssignableBookingGetNumberOfUnassignedBookingShouldReturnOne() {
		setUpOneAssignableBookingInBookings();
		assertEquals(1, bookings.getNumberOfAssignableBookings());
	}

	@Test
	public void givenZeroUnassignedBookingGetNumberOfUnassignedBookingShouldReturnZero() {
		setUpAllUnassignableBookings();
		assertEquals(0, bookings.getNumberOfAssignableBookings());
	}

	@Test
	public void givenARepositoryWithAllUnassignableBookingBookingsShouldNotHaveAssignableBooking() {
		setUpAllUnassignableBookings();
		assertFalse(bookings.hasAssignableBookings());
	}

	@Test
	public void givenOneAssignableBookingassignBookingsToBoardroomsShouldPersistAssignableBooking() {
		setUpOneAssignableBookingInBookings();

		bookings.assignBookingsToBoardrooms(boardrooms, bookingsSortingStrategy, boardroomsSortingStrategy);
		verify(bookingRepository).persist(assignableBooking);
	}

	private void setUpBookingStrategyMock() {
		List<Booking> formatedList = new ArrayList<Booking>(Arrays.asList(booking));
		when(bookingsSortingStrategy.sort(any())).thenReturn(formatedList);
	}

	private void setUpEmptyBookings() {
		emptyBookingCollection = new ArrayList<Booking>();
		doReturn(emptyBookingCollection).when(bookingRepository).retrieveAll();
	}

	private void setUpAllUnassignableBookings() {
		bookingsNoAssignabledBooking = new ArrayList<Booking>();

		when(bookingsSortingStrategy.sort(any())).thenReturn(bookingsNoAssignabledBooking);
		when(bookingRepository.getAssignableBookings()).thenReturn(bookingsNoAssignabledBooking);
	}

	private void setUpOneAssignableBookingInBookings() {
		bookingsWithOneAssignablBookings = new ArrayList<Booking>();
		bookingsWithOneAssignablBookings.add(assignableBooking);

		doReturn(bookingsWithOneAssignablBookings).when(bookingRepository).retrieveAll();
		doReturn(bookingsWithOneAssignablBookings).when(bookingRepository).retrieveSortedByPriority();
		when(bookingsSortingStrategy.sort(any())).thenReturn(bookingsWithOneAssignablBookings);
		when(bookingRepository.getAssignableBookings()).thenReturn(bookingsNoAssignabledBooking);
	}
}