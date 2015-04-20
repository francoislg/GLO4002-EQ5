package ca.ulaval.glo4002.GRAISSE.core.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	@Mock
	BookingRepository bookingRepository;

	@Mock
	Booking booking;

	@Mock
	Booking booking2;

	@Mock
	Booking assignableBooking;

	@Mock
	Boardrooms boardrooms;

	@Mock
	BookingsSortingStrategy bookingsSortingStrategy;

	@Mock
	BoardroomsSortingStrategy boardroomsSortingStrategy;

	Collection<Booking> bookingsWithoutAssignableBookings;

	Bookings bookings;

	@Before
	public void setUp() {
		bookings = new Bookings(bookingRepository);
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
	public void givenZeroUnassignedBookingGetNumberOfUnassignedBookingShouldReturnZero() {
		setUpEmptyBookings();
		assertEquals(0, bookings.getNumberOfAssignableBookings());
	}

	@Test
	public void givenARepositoryWithAllUnassignableBookingBookingsShouldNotHaveAssignableBooking() {
		setUpEmptyBookings();
		assertFalse(bookings.hasAssignableBookings());
	}

	private void setUpEmptyBookings() {
		Collection<Booking> emptyBookingCollection = new ArrayList<Booking>();
		doReturn(emptyBookingCollection).when(bookingRepository).getAssignableBookings();
		when(bookingsSortingStrategy.sort(any())).thenReturn(emptyBookingCollection);
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

	private void setUpOneAssignableBookingInBookings() {
		Collection<Booking> bookingsWithOneAssignableBookings = new ArrayList<Booking>();
		bookingsWithOneAssignableBookings.add(assignableBooking);

		doReturn(bookingsWithOneAssignableBookings).when(bookingRepository).getAssignableBookings();
		when(bookingsSortingStrategy.sort(any())).thenReturn(bookingsWithOneAssignableBookings);
	}

	@Ignore
	@Test
	public void givenAnEmailWhenRetrievingBookingsWithEmailShouldReturnAllBookingsWithTheEmail() {

	}

	@Ignore
	@Test
	public void getBookingsForEmailShouldReturnAListOfBookingDTO() {

	}
}