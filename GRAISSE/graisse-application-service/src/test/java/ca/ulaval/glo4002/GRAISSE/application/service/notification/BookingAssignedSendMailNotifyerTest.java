package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import static ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMatcher.withAMailSentTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingAssignedSendMailNotifyerTest {

	private static final String USER_EMAIL = "email@totalyanemail.ca";
	private static final String RESPONSIBLE_EMAIL = "email@responsible.com";

	@Mock
	Booking booking;

	@Mock
	User user;

	@Mock
	User responsible;

	@Mock
	MailSender mailSender;

	BookingAssignedSendMailNotifyer bookingAssignedSendMailNotifyer;

	@Before
	public void setUp() throws Exception {
		setUpUsersMocks();
		setUpBookingMock();
		bookingAssignedSendMailNotifyer = new BookingAssignedSendMailNotifyer(mailSender, responsible);
	}

	@Test
	public void givenBookingAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(true);

		bookingAssignedSendMailNotifyer.notify(booking);

		verify(mailSender).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailSender).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}

	@Test
	public void givenBookingNotAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(false);

		bookingAssignedSendMailNotifyer.notify(booking);

		verify(mailSender).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailSender).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}

	private void setUpBookingMock() {
		when(booking.hasPromoter(new Email(USER_EMAIL))).thenReturn(true);
		when(booking.getPromoterEmail()).thenReturn(new Email(USER_EMAIL));
	}

	private void setUpUsersMocks() {
		when(responsible.getEmail()).thenReturn(new Email(RESPONSIBLE_EMAIL));
	}
}