package ca.ulaval.glo4002.GRAISSE.rest.interfaces.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;

@RunWith(MockitoJUnitRunner.class)
public class RetrievedBookingResponseTest {
	private static final String PROMOTER_EMAIL = "PROMOTER_EMAIL";
	private static final String ANOTHER_PROMOTER_EMAIL = "ANOTHER_PROMOTER_EMAIL";

	@Mock
	private BookingDTO bookingDTO;

	private RetrievedBookingResponse response;

	@Before
	public void setUp() {
		setUpBookingDTOMock();
		response = new RetrievedBookingResponse(bookingDTO);
	}

	@Test
	public void courrielOrganisateurShouldBeTheSameAsGiven() {
		assertEquals(PROMOTER_EMAIL, response.courrielOrganisateur);
	}

	@Test
	public void givenObjectWithSameParameterShouldBeEquals() {
		assertTrue(response.equals(new RetrievedBookingResponse(bookingDTO)));
	}

	@Test
	public void givenADifferentPromoterEmailShouldNotBeEquals() {
		when(bookingDTO.getPromoterEmail()).thenReturn(ANOTHER_PROMOTER_EMAIL);
		assertFalse(response.equals(new RetrievedBookingResponse(bookingDTO)));
	}

	private void setUpBookingDTOMock() {
		when(bookingDTO.getPromoterEmail()).thenReturn(PROMOTER_EMAIL);
	}
}
