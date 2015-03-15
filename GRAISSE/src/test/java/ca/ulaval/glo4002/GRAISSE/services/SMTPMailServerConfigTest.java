package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import javax.mail.Session;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailServerConfigTest {	
	Session session;
	
	SMTPMailServerConfig smtpMailServerConfig;
	
	@Before
	public void setUp() throws Exception {
		smtpMailServerConfig = new SMTPMailServerConfig(session);
	}
	
	@Test
	public void getSessionShouldReturnSession(){
		assertEquals(session, smtpMailServerConfig.getSession());
	}

}
