package ca.ulaval.glo4002.GRAISSE.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailSenderTest {

	@Mock
	SMTPMessageFactory smtpMessageFactory;

	@Mock
	SMTPMailSession mailSession;

	@Mock
	Mail mail;

	@Mock
	Transport transport;

	@Mock
	Message message;

	SMTPMailSender smtpMailSender;

	@Before
	public void setUp() throws MessagingException {
		smtpMailSender = new SMTPMailSender(smtpMessageFactory, mailSession);
		when(smtpMessageFactory.create(mail, mailSession)).thenReturn(message);
	}

	@Test
	public void sendShouldSendTheMessage() throws MessagingException {
		smtpMailSender.send(mail);
		verify(transport).sendMessage(message, message.getAllRecipients());
	}
}
