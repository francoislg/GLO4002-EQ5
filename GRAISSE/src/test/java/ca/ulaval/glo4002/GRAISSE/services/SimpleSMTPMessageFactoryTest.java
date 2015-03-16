package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleSMTPMessageFactoryTest {
	
	private static final String MAIL_DESTINATION = "Destination";
	private static final String MAIL_SUBJECT = "Title";
	private static final String MAIL_MESSAGE = "Message";
	
	@Mock
	Mail mail;
	
	Session session;
	SimpleSMTPMessageFactory smtpMessageFactory;
	
	@Before
	public void setUp() {
		setUpMailMock();
		smtpMessageFactory = new SimpleSMTPMessageFactory();
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
		
		assertThat(Arrays.asList(message.getAllRecipients()), hasItem(expectedAddress));
	}
	
	@Test
	public void createShouldReturnAMessageWithSubjectFromMail() throws MessagingException {
		Message message = smtpMessageFactory.create(mail, session);
		assertEquals(MAIL_SUBJECT, message.getSubject());
	}
	
	@Test
	public void createShouldReturnAMessageWithBodyFromMail() throws MessagingException, IOException {
		Message message = smtpMessageFactory.create(mail, session);
		assertEquals(MAIL_MESSAGE, message.getContent().toString());
	}
	
	private void setUpMailMock(){
		when(mail.getDestinationString()).thenReturn(MAIL_DESTINATION);
		when(mail.getSubject()).thenReturn(MAIL_SUBJECT);
		when(mail.getMessage()).thenReturn(MAIL_MESSAGE);
	}
}