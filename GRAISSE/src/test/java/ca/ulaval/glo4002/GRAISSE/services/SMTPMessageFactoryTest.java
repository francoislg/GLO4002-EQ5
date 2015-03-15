package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMessageFactoryTest {
	
	@Mock
	Mail mail;
	
	Session session;
	
	
	SMTPMessageFactory smtpMessageFactory;
	
	@Before
	public void setUp() throws Exception {
		smtpMessageFactory = new SMTPMessageFactory();
	}

	@Test
	public void testCreateShouldReturnAMessage() throws MessagingException {
		smtpMessageFactory.create(mail, session);
	}

}
