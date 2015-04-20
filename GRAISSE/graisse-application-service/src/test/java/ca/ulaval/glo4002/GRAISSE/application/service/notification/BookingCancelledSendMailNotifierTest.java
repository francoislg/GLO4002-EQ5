package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import static org.mockito.Matchers.any;
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

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingCancelledSendMailNotifierTest {
	private static final String USER_EMAIL = "A_USER_EMAIL@email.ca";

	BookingCancelledSendMailNotifier bookingCancelledNotifyer;

	@Mock
	private MailSender mailSender;

	@Mock
	private User user;

	@Mock
	private Email userEmail;

	@Mock
	private User responsible;

	@Mock
	private Email responsibleEmail;

	@Mock
	private MailMessage mail;

	@Mock
	private AssignedBooking booking;

	@Before
	public void setUp() throws Exception {
		when(user.getEmail()).thenReturn(userEmail);
		when(responsible.getEmail()).thenReturn(responsibleEmail);

		setUpBookingMock();
		bookingCancelledNotifyer = new BookingCancelledSendMailNotifier(mailSender, user, responsible);
	}

	@Test
	public void givenBookingCancelledWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		setUpBookingMock();
		when(booking.isAssigned()).thenReturn(true);
		when(booking.hasPromoter(any())).thenReturn(true);

		bookingCancelledNotifyer.notify(booking);

		verify(mailSender).sendMail(any());
	}

	@Ignore
	@Test
	public void givenBookingCancelledWhenNotifiedShouldSendMailToBookingParticipants() {

	}

	private void setUpBookingMock() {
		Collection<Email> emailCollection = new ArrayList<>();
		Email userEmail = new Email(USER_EMAIL);
		emailCollection.add(userEmail);

		when(booking.hasPromoter(responsibleEmail)).thenReturn(true);
		when(booking.getParticipantsEmail()).thenReturn(emailCollection);
	}

}
