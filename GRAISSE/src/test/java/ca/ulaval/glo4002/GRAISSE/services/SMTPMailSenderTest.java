package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

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
	
	SMTPMailSender smtpMailSender;
	
	@Before
	public void setUp(){
		smtpMailSender = new SMTPMailSender(smtpMessageFactory, mailSession);
	}
	
	@Test
	public void testConnect() {
		smtpMailSender.connect();
	}

	@Test
	public void testSend() {
		fail("Not yet implemented");
	}

	@Test
	public void testDisconnect() {
		fail("Not yet implemented");
	}

}
