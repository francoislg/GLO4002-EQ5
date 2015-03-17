package ca.ulaval.glo4002.GRAISSE.services;

import static org.mockito.Mockito.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotCloseConnectionException;
import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailSenderTest {
	
	@Mock
	SMTPMessageFactory smtpMessageFactory;
	
	@Mock
	SMTPMailSession mailSession;
	
	@Mock
	Transport transport;
	
	@Mock
	Mail mail;
	
	@Mock
	Message message;
	
	Address[] addresses;
	SMTPMailSender smtpMailSender;
	
	@Before
	public void setUp(){
		setUpSMTPMessageFactory();
		when(mailSession.getSMTPTransport()).thenReturn(transport);
		smtpMailSender = new SMTPMailSender(smtpMessageFactory, mailSession);
	}
	
	@Test
	public void sendingMailShouldConnectMailSender() {
		smtpMailSender.send(mail);
		verify(mailSession).connect(transport);
	}

	@Test
	public void sendShouldSendAMessageWithTransport() throws MessagingException {
		smtpMailSender.send(mail);
		verify(transport).sendMessage(message, addresses);
	}

	@Test
	public void sendingMailShouldDisconnectTransport() throws MessagingException {
		smtpMailSender.send(mail);
		verify(transport).close();
	}
	
	@Test(expected=CouldNotSendMailException.class)
	public void givenTransportThrowsWhenSendingMessageShouldThrowException() throws MessagingException {
		doThrow(new MessagingException()).when(transport).sendMessage(message, addresses);
		smtpMailSender.send(mail);
	}
	
	@Test(expected=CouldNotCloseConnectionException.class)
	public void givenTransportThrowsWhenClosingShouldThrowException() throws MessagingException {
		doThrow(new MessagingException()).when(transport).close();
		smtpMailSender.send(mail);
	}
	
	private void setUpSMTPMessageFactory() {
		when(smtpMessageFactory.create(mail, mailSession)).thenReturn(message);
		try {
			when(message.getAllRecipients()).thenReturn(addresses);
		} catch (MessagingException e) {}
	}
}
