package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import static ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMatcher.withAMailSentTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingCancelledSendMailNotifyerTest {
	private static final String USER_EMAIL = "A_USER_EMAIL@email.ca";
	private static final String RESPONSIBLE_EMAIL = "RESPONSIBLE@email.ca";

	BookingCancelledSendMailNotifyer bookingCancelledNotifyer;

	@Mock
	private MailSender mailSender;

	@Mock
	private User user;

	@Mock
	private User responsible;

	@Mock
	private BookingAssignable booking;

	@Before
	public void setUp() throws Exception {
		setUpBookingMock();
		bookingCancelledNotifyer = new BookingCancelledSendMailNotifyer(mailSender, user, responsible);
	}

	@Ignore
	@Test
	public void givenBookingCancelledWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(true);

		bookingCancelledNotifyer.notify(booking);

		verify(mailSender).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailSender).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}

	@Ignore
	@Test
	public void givenBookingCancelledWhenNotifiedShouldSendMailToBookingParticipants() {

	}

	private void setUpBookingMock() {
		when(booking.hasCreator(user)).thenReturn(true);
	}
}
