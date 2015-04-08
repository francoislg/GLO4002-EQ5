package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.notification.BookingAssignedSendMailNotifyer;
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

	BookingAssignedSendMailNotifyer bookingAssignedSendMailTrigger;

	@Before
	public void setUp() throws Exception {
		setUpUsersMocks();
		setUpBookingMock();
		bookingAssignedSendMailTrigger = new BookingAssignedSendMailNotifyer(mailSender, user, responsible);
	}

	@Test
	public void givenBookingAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(true);

		bookingAssignedSendMailTrigger.notify(booking);

		verify(mailSender).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailSender).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}

	@Test
	public void givenBookingNotAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(false);

		bookingAssignedSendMailTrigger.notify(booking);

		verify(mailSender).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailSender).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}

	private MailMessage withAMailSentTo(String email) {
		return argThat(IsAMailSentTo(email));
	}

	private static BaseMatcher<MailMessage> IsAMailSentTo(final String email) {
		return new BaseMatcher<MailMessage>() {
			@Override
			public boolean matches(Object argument) {
				final MailMessage mail = (MailMessage) argument;
				return mail.getDestinationString().equals(email);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("destination for mail should be ").appendText(email);
			}
		};
	}

	private void setUpBookingMock() {
		when(booking.hasCreator(user)).thenReturn(true);
	}

	private void setUpUsersMocks() {
		when(user.getEmail()).thenReturn(new Email(USER_EMAIL));
		when(responsible.getEmail()).thenReturn(new Email(RESPONSIBLE_EMAIL));
	}
}