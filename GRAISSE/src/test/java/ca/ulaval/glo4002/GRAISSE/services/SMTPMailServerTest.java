package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailServerTest {	
	private final static String DESTINATION = "DESTINATION";
	
	@Mock
	JavaMailMailSender mailSender;
	
	@Mock
	SMTPMailServerConfig stmpMailServerConfig;
	
	@Mock
	MessageFactory messageFactory;
	
	Session session;
	
	@Mock
	Message message;
	
	@Mock
	Mail mail;
	
	SMTPMailServer smtpMailServer;
	
	@Before
	public void setUp() throws Exception {
		setUpMailMock();
		setUpSMTPMailServerConfigMock();
		setUpMessageFactoryMock();
		smtpMailServer = new SMTPMailServer(stmpMailServerConfig, mailSender, messageFactory);
	}

	@Test
	public void testSendMailShouldSendMail() throws MessagingException {
		smtpMailServer.sendMail(mail);
		verify(mailSender).send(any(Message.class));
	}
	
	private void setUpSMTPMailServerConfigMock(){
		when(stmpMailServerConfig.getDefaultSession()).thenReturn(session);
	}
	
	private void setUpMailMock(){
		when(mail.getDestinationString()).thenReturn(DESTINATION);
	}
	
	private void setUpMessageFactoryMock() throws MessagingException{
		when(messageFactory.create(mail, session)).thenReturn(message);
	}
}
