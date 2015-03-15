package ca.ulaval.glo4002.GRAISSE.services;

import static org.mockito.Mockito.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailServerTest {
	@Mock
	Address aRecipient;
	
	@Mock
	Transport transport;
	
	@Mock
	SMTPMailServerConfig stmpMailServerConfig;
	
	@Mock
	SMTPMessageFactory messageFactory;
	
	Session session;
	
	@Mock
	Message message;
	
	@Mock
	Mail mail;
	
	@Mock
	Mail invalidMail;
	
	SMTPMailServer smtpMailServer;
	
	@Before
	public void setUp() throws Exception {
		setUpSMTPMailServerConfigMock();
		setUpMessageFactoryMock();
		setUpMessageMock();
		smtpMailServer = new SMTPMailServer(stmpMailServerConfig, transport, messageFactory);
	}

	@Test
	public void testSendMailShouldSendMail() throws MessagingException {
		smtpMailServer.sendMail(mail);
		verify(transport).sendMessage(message, message.getAllRecipients());
	}
	
	@Test(expected=CouldNotSendMailException.class)
	public void testSendInvalidMailShouldThrowAnException() throws MessagingException {
		smtpMailServer.sendMail(invalidMail);
	}
	
	private void setUpSMTPMailServerConfigMock(){
		when(stmpMailServerConfig.getSession()).thenReturn(session);
	}
	
	private void setUpMessageFactoryMock() throws MessagingException{
		when(messageFactory.create(mail, session)).thenReturn(message);
		when(messageFactory.create(invalidMail, session)).thenThrow(new MessagingException());
	}
	
	private void setUpMessageMock() throws MessagingException{
		when(message.getAllRecipients()).thenReturn(new Address[]{aRecipient});
	}
}
