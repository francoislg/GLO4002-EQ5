package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotConnectException;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailSessionTest {
	
	private static final String USERNAME = "Username";
	private static final String PASSWORD = "Password";
	
	Session session;
	
	@Mock
	Transport transport;
	
	SMTPMailSession smtpMailSession;
	
	@Before
	public void setUp() throws Exception {
		session = getAnySession();
		smtpMailSession = new SMTPMailSession(session, USERNAME, PASSWORD);
	}

	@Test
	public void getSMTPTransportShouldReturnATransportObject() {
		Transport transport = smtpMailSession.getSMTPTransport();
		assertNotNull(transport);
	}

	@Test
	public void connectShouldCallConnectOnTransport() throws MessagingException {
		smtpMailSession.connect(transport);
		verify(transport).connect(USERNAME, PASSWORD);
	}
	
	@Test(expected=CouldNotConnectException.class)
	public void givenTransportThrowErrorWhenConnectionShouldThrowException() throws MessagingException {
		doThrow(new MessagingException()).when(transport).connect(USERNAME, PASSWORD);
		smtpMailSession.connect(transport);
	}

	@Test
	public void getNewMessageShouldReturnAMessageObject() {
		Message message = smtpMailSession.getNewMessage();
		assertNotNull(message);
	}
	
	public Session getAnySession(){
		return Session.getDefaultInstance(new Properties());
	}
}
