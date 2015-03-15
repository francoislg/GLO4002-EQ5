package ca.ulaval.glo4002.GRAISSE.booking;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import java.util.Collection;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.Email;
import ca.ulaval.glo4002.GRAISSE.services.Mail;
import ca.ulaval.glo4002.GRAISSE.services.MailServer;
import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;
import ca.ulaval.glo4002.GRAISSE.user.User;

@RunWith(MockitoJUnitRunner.class)
public class BookingAssignedSendMailTriggerTest {
	private final static String USER_EMAIL = "email@totalyanemail.ca";
	private final static String RESPONSIBLE_EMAIL = "email@responsible.com";
	
	@Mock
	Booking booking;
	
	@Mock
	User user;
	
	@Mock
	User responsible;
	
	@Mock
	MailServer mailServer;
	
	BookingAssignedSendMailTrigger bookingAssignedSendMailTrigger;
	
	@Before
	public void setUp() throws Exception {
		setUpUsersMocks();
		setUpBookingMock();
		bookingAssignedSendMailTrigger = new BookingAssignedSendMailTrigger(mailServer, user, responsible);
	}

	@Test
	public void givenBookingAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(true);
		
		bookingAssignedSendMailTrigger.update(booking);
		
		verify(mailServer).sendMail(argThat(IsAMailSentTo(USER_EMAIL)));
		verify(mailServer).sendMail(argThat(IsAMailSentTo(RESPONSIBLE_EMAIL)));
	}
	
	@Test
	public void givenBookingNotAssignedWithUserAsCreatorWhenNotifiedShouldSendMailToUserAndResponsible() {
		when(booking.isAssigned()).thenReturn(false);
		
		bookingAssignedSendMailTrigger.update(booking);
		
		verify(mailServer).sendMail(withAMailSentTo(USER_EMAIL));
		verify(mailServer).sendMail(withAMailSentTo(RESPONSIBLE_EMAIL));
	}
	
	@Test(expected=CouldNotSendMailException.class)
	public void whenMailServerFailedToSendAnEmailShouldThrowException() {
		when(booking.isAssigned()).thenReturn(false);
		doThrow(new CouldNotSendMailException()).when(mailServer).sendMail(any(Mail.class));
		
		bookingAssignedSendMailTrigger.update(booking);
	}
	
	private Mail withAMailSentTo(String email){
		return argThat(IsAMailSentTo(email));
	}
	
	private static BaseMatcher<Mail> IsAMailSentTo(final String email) {
		return new BaseMatcher<Mail>() {
			@Override
			public boolean matches(Object argument) {
				final Mail mail = (Mail)argument;
				return mail.getDestinationString().equals(email);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("destination for mail should be ").appendText(email);
			}
		};
    }
	
	private void setUpBookingMock(){
		when(booking.hasCreator(user)).thenReturn(true);
	}
	
	private void setUpUsersMocks(){
		when(user.getEmail()).thenReturn(new Email(USER_EMAIL));
		when(responsible.getEmail()).thenReturn(new Email(RESPONSIBLE_EMAIL));
	}
}