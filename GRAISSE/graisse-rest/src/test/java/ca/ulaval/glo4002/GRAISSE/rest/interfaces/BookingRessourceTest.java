package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.UserRepository;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.AddBookingForm;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.BookingsForEmailResponse;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@RunWith(MockitoJUnitRunner.class)
public class BookingRessourceTest {

	private static final String A_RESERVATION_ID = "RandomReservationName";
	private static final String A_BOOKING_ID = "ItsABooking";
	private static final String A_NON_EXISTING_ID = "DoesntExistSorryBro";
	private static final int A_NUMBER_OF_SEATS = 1;
	private static final String PROMOTER_EMAIL = "Email@something.ca";
	private static final String INVALID_EMAIL = "INVALID";
	private static final BookingState ANY_STATE = BookingState.ASSIGNED;
	private static final int A_PRIORITY = 2;
	private static final String AN_INVALID_EMAIL = "INVALID";

	@Mock
	Booker booker;

	@Mock
	Bookings bookings;

	@Mock
	Reservations reservations;

	@Mock
	UserRepository userRepository;

	@Mock
	BookingDTO bookingDTO;

	BookingRessource bookingRessource;

	@Before
	public void setUp() throws Exception {
		setUpBookerMock();
		setUpBookingMock();
		bookingRessource = new BookingRessource(booker, bookings, reservations, userRepository);
	}

	@Test
	public void getBookingWithValidUserAndReservationIDShouldReturnAValidObject() {
		RetrievedBookingResponse expectedResponse = new RetrievedBookingResponse(bookingDTO);

		RetrievedBookingResponse response = bookingRessource.getBooking(PROMOTER_EMAIL, A_RESERVATION_ID);

		assertEquals(expectedResponse, response);
	}

	@Test
	public void getBookingWithValidUserAndBookingIDShouldReturnAValidObject() {
		RetrievedBookingResponse expectedResponse = new RetrievedBookingResponse(bookingDTO);

		RetrievedBookingResponse response = bookingRessource.getBooking(PROMOTER_EMAIL, A_BOOKING_ID);

		assertEquals(expectedResponse, response);
	}

	@Test
	public void givenValidRequestAddNewBookingShouldAddTheBookingInBooker() {
		AddBookingForm form = getAddBookingForm();

		bookingRessource.addNewBooking(form);

		verify(booker).addBooking(any(Booking.class));
	}

	@Test
	public void givenValidRequestAddNewBookingShouldReturnAResponse() {
		AddBookingForm form = getAddBookingForm();

		Response response = bookingRessource.addNewBooking(form);

		assertNotNull(response);
	}

	@Test(expected = InvalidEmailWebException.class)
	public void givenInvalidRequestShouldThrowException() {
		AddBookingForm form = getAddBookingForm();
		form.courrielOrganisateur = AN_INVALID_EMAIL;

		bookingRessource.addNewBooking(form);
	}

	@Test(expected = BookingNotFoundWebException.class)
	public void getBookingWithInvalidBookingIDShouldThrowAnException() {
		bookingRessource.getBooking(PROMOTER_EMAIL, A_NON_EXISTING_ID);
	}

	@Test(expected = InvalidEmailWebException.class)
	public void givenInvalidEmailgetBookingForEmailShouldThrowAnException() {
		bookingRessource.getBookingForEmail(INVALID_EMAIL);
	}

	@Test
	public void getBookingForEmailShouldReturnAListOfBookingForEmail() {
		List<BookingDTO> bookingDTOs = getListOfBookingDTO();
		when(bookings.getBookingsWithEmail(new Email(PROMOTER_EMAIL))).thenReturn(bookingDTOs);
		BookingsForEmailResponse expectedResponse = new BookingsForEmailResponse(bookingDTOs);

		BookingsForEmailResponse result = bookingRessource.getBookingForEmail(PROMOTER_EMAIL);

		assertEquals(expectedResponse, result);
	}

	private void setUpBookingMock() {
		when(bookingDTO.getNumberOfSeats()).thenReturn(A_NUMBER_OF_SEATS);
		when(bookingDTO.getPromoterEmail()).thenReturn(PROMOTER_EMAIL);
		when(bookingDTO.getBookingState()).thenReturn(ANY_STATE);
		when(bookingDTO.getID()).thenReturn(new BookingID(A_BOOKING_ID));
	}

	private void setUpBookerMock() {
		Email promoter_email = new Email(PROMOTER_EMAIL);
		BookingID existingReservationID = new BookingID(A_RESERVATION_ID);
		BookingID existingBookingID = new BookingID(A_BOOKING_ID);
		BookingID nonExistingID = new BookingID(A_NON_EXISTING_ID);

		when(reservations.hasReservation(promoter_email, existingReservationID)).thenReturn(true);
		when(reservations.hasReservation(promoter_email, existingBookingID)).thenReturn(false);
		when(reservations.hasReservation(promoter_email, nonExistingID)).thenReturn(false);

		when(bookings.hasBooking(promoter_email, existingBookingID)).thenReturn(true);
		when(bookings.hasBooking(promoter_email, nonExistingID)).thenReturn(false);

		when(reservations.retrieveReservationDTO(promoter_email, existingReservationID)).thenReturn(bookingDTO);
		when(bookings.retrieveDTO(promoter_email, existingBookingID)).thenReturn(bookingDTO);
	}

	private AddBookingForm getAddBookingForm() {
		AddBookingForm addBookingForm = new AddBookingForm();
		addBookingForm.courrielOrganisateur = PROMOTER_EMAIL;
		addBookingForm.nombrePersonne = A_NUMBER_OF_SEATS;
		addBookingForm.priorite = A_PRIORITY;
		return addBookingForm;
	}

	private List<BookingDTO> getListOfBookingDTO() {
		BookingDTO dto = new BookingDTO(new BookingID(A_BOOKING_ID), A_NUMBER_OF_SEATS, PROMOTER_EMAIL, ANY_STATE, "");
		return new ArrayList<BookingDTO>(Arrays.asList(dto));
	}
}
