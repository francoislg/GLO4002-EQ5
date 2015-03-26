package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotCreateMessageException;

@RunWith(MockitoJUnitRunner.class)
public class JavaMailMessageFactoryTest {

	private static final String MAIL_DESTINATION = "Destination";
	private static final String MAIL_SUBJECT = "Title";
	private static final String MAIL_MESSAGE = "Message";

	@Mock
	MailMessage mail;

	@Mock
	Message message;

	Session session;
	JavaMailMessageFactory smtpMessageFactory;

	@Before
	public void setUp() {
		setUpMailMock();
		smtpMessageFactory = new JavaMailMessageFactory();
	}

	@Test
	public void createShouldReturnAMessage() throws MessagingException {
		Message message = smtpMessageFactory.create(mail, session);
		assertNotNull(message);
	}

	@Test
	public void createShouldReturnAMessageWithRecipientFromMail() throws MessagingException {
		Address expectedAddress = new InternetAddress(MAIL_DESTINATION);

		Message message = smtpMessageFactory.create(mail, session);

		verify(message).addRecipient(Message.RecipientType.TO, expectedAddress);
	}

	@Test
	public void createShouldAddContentToMessage() throws MessagingException {
		smtpMessageFactory.create(mail, session);
		verify(message).setText(MAIL_MESSAGE);
	}

	@Test
	public void createShouldAddSubjectToMessage() throws MessagingException, IOException {
		smtpMessageFactory.create(mail, session);
		verify(message).setSubject(MAIL_SUBJECT);
	}

	@Test(expected = CouldNotCreateMessageException.class)
	public void givenFaultyEmailCreateShouldThrowException() throws MessagingException {
		Address addressThatWillFail = new InternetAddress(MAIL_DESTINATION);
		doThrow(new MessagingException()).when(message).addRecipient(RecipientType.TO, addressThatWillFail);
		smtpMessageFactory.create(mail, session);
	}

	private void setUpMailMock() {
		when(mail.getDestinationString()).thenReturn(MAIL_DESTINATION);
		when(mail.getSubject()).thenReturn(MAIL_SUBJECT);
		when(mail.getMessage()).thenReturn(MAIL_MESSAGE);
	}
}