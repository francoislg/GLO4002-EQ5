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
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomsSortingStrategy;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@RunWith(MockitoJUnitRunner.class)
public class BookingsTest {

	private final static BookingState A_BOOKING_STATE = BookingState.WAITING;
	private final static int A_NUMBER_OF_SEATS = 10;
	private final static String AN_EMAIL = "EMAIL@DOMAIN.COM";

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

	@Mock
	Email email;

	@Mock
	BookingID bookingID;

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

	@Test
	public void givenAbookingsSortingStrategygetBookingsWithStrategyShouldCallTheStrategyWithTheRepository() {
		bookings.getBookingsWithStrategy(bookingsSortingStrategy);
		verify(bookingsSortingStrategy).sort(bookingRepository);
	}

	@Test
	public void givenABookingassignBookingShouldAssignTheBooking() {
		bookings.assignBooking(booking);
		verify(booking).assign();
	}

	@Test
	public void givenABookingassignBookingShouldpersistTheBooking() {
		bookings.assignBooking(booking);
		verify(bookingRepository).persist(booking);
	}

	@Test
	public void givenAEmailgetBookingsWithEmailShouldReturnAllTheBookingAssociatedWithTheEmail() {
		setUpBookingForDTO();
		Collection<Booking> bookingCollection = new ArrayList<Booking>();
		bookingCollection.add(booking);
		when(bookingRepository.retrieveAllForEmail(email)).thenReturn(bookingCollection);
		List<BookingDTO> result = bookings.getBookingsWithEmail(email);
		assertEquals(1, result.size());
	}

	@Test
	public void givenAEmailAndABookingIDretrieveDTOShouldReturnABookingDTOWithValidInformation() {
		setUpBookingForDTO();
		when(bookingRepository.retrieve(email, bookingID)).thenReturn(booking);
		BookingDTO bookingDTO = bookings.retrieveDTO(email, bookingID);
		assertEquals(email.getValue(), bookingDTO.getPromoterEmail());
	}

	@Test
	public void givenABookingIDAndAEmailForABookingThatExisthasBookingShouldReturnTrue() {
		when(bookingRepository.exists(email, bookingID)).thenReturn(true);
		assertTrue(bookings.hasBooking(email, bookingID));
	}

	@Test
	public void givenABookingIDAndAEmailForABookingThatDoesNotExisthasBookingShouldReturnFalse() {
		when(bookingRepository.exists(email, bookingID)).thenReturn(false);
		assertFalse(bookings.hasBooking(email, bookingID));
	}

	private void setUpBookingForDTO() {
		when(booking.getID()).thenReturn(bookingID);
		when(booking.getNumberOfSeats()).thenReturn(A_NUMBER_OF_SEATS);
		when(booking.getPromoterEmail()).thenReturn(email);
		when(email.getValue()).thenReturn(AN_EMAIL);
		when(booking.getState()).thenReturn(A_BOOKING_STATE);

	}

	private void setUpOneAssignableBookingInBookings() {
		Collection<Booking> bookingsWithOneAssignableBookings = new ArrayList<Booking>();
		bookingsWithOneAssignableBookings.add(assignableBooking);

		doReturn(bookingsWithOneAssignableBookings).when(bookingRepository).getAssignableBookings();
		when(bookingsSortingStrategy.sort(any())).thenReturn(bookingsWithOneAssignableBookings);
	}

}