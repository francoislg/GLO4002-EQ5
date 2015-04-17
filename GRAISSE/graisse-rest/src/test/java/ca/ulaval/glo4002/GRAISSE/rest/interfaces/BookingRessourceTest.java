package ca.ulaval.glo4002.GRAISSE.rest.interfaces;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingNotFoundException;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.BookingNotFoundWebException;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.BookingRessource;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class BookingRessourceTest {

	private static final String A_BOOKING_NAME = "RandomBookingName";
	private static final String A_NON_EXISTING_BOOKING_NAME = "DoesntExistSorryBro";
	private static final int A_NUMBER_OF_SEATS = 1;
	private static final String PROMOTER_EMAIL = "Email@something.ca";
	private static final BookingState ANY_STATE = BookingState.ASSIGNED;

	@Mock
	BookingRepository bookingRepository;

	@Mock
	Booking booking;

	BookingRessource bookingRessource;

	@Before
	public void setUp() throws Exception {
		setUpBookingMock();
		setUpBookingRepositoryMock();
		bookingRessource = new BookingRessource(bookingRepository);
	}

	@Test
	public void getBookingWithValidUserAndIDShouldReturnAValidObject() {
		RetrievedBookingResponse expectedResponse = new RetrievedBookingResponse(booking);

		RetrievedBookingResponse response = bookingRessource.getBooking(PROMOTER_EMAIL, A_BOOKING_NAME);

		assertEquals(expectedResponse, response);
	}

	@Test(expected = BookingNotFoundWebException.class)
	public void getBookingWithInvalidBookingShouldThrowBookingNotFoundWebException() {
		bookingRessource.getBooking(PROMOTER_EMAIL, A_NON_EXISTING_BOOKING_NAME);
	}

	private void setUpBookingMock() {
		when(booking.getName()).thenReturn(A_BOOKING_NAME);
		when(booking.getNumberOfSeats()).thenReturn(A_NUMBER_OF_SEATS);
		when(booking.getPromoterEmail()).thenReturn(PROMOTER_EMAIL);
		when(booking.getState()).thenReturn(ANY_STATE);
	}

	private void setUpBookingRepositoryMock() {
		when(bookingRepository.retrieve(new Email(PROMOTER_EMAIL), A_BOOKING_NAME)).thenReturn(booking);
		when(bookingRepository.retrieve(new Email(PROMOTER_EMAIL), A_NON_EXISTING_BOOKING_NAME)).thenThrow(new BookingNotFoundException());
	}
}
