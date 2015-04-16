package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class BookingRessourceTest {

	private static final String A_RESERVATION_ID = "RandomBookingName";
	private static final String A_NON_EXISTING_RESERVATION_ID = "DoesntExistSorryBro";
	private static final int A_NUMBER_OF_SEATS = 1;
	private static final String PROMOTER_EMAIL = "Email@something.ca";
	private static final BookingState ANY_STATE = BookingState.ASSIGNED;

	@Mock
	Booker booker;

	@Mock
	Bookings bookings;

	@Mock
	Reservations reservations;

	@Mock
	BookingDTO bookingDTO;

	BookingRessource bookingRessource;

	@Before
	public void setUp() throws Exception {
		setUpBookerMock();
		setUpBookingMock();
		bookingRessource = new BookingRessource(booker, bookings, reservations);
	}

	@Test
	public void getBookingWithValidUserAndIDShouldReturnAValidObject() {
		RetrievedBookingResponse expectedResponse = new RetrievedBookingResponse(bookingDTO);

		RetrievedBookingResponse response = bookingRessource.getBooking(PROMOTER_EMAIL, A_RESERVATION_ID);

		assertEquals(expectedResponse, response);
	}

	@Test(expected = BookingNotFoundWebException.class)
	public void getBookingWithInvalidBookingShouldThrowBookingNotFoundWebException() {
		bookingRessource.getBooking(PROMOTER_EMAIL, A_NON_EXISTING_RESERVATION_ID);
	}

	private void setUpBookingMock() {
		when(bookingDTO.getNumberOfSeats()).thenReturn(A_NUMBER_OF_SEATS);
		when(bookingDTO.getPromoterEmail()).thenReturn(PROMOTER_EMAIL);
		when(bookingDTO.getBookingState()).thenReturn(ANY_STATE);
	}

	private void setUpBookerMock() {
		when(reservations.retrieveReservation(new Email(PROMOTER_EMAIL), new BookingID(A_RESERVATION_ID))).thenReturn(bookingDTO);
		when(reservations.retrieveReservation(new Email(PROMOTER_EMAIL), new BookingID(A_NON_EXISTING_RESERVATION_ID))).thenThrow(
				new ReservationNotFoundException());
	}
}
