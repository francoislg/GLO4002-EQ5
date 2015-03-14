package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMailTest {
	private static final String MAIL_MESSAGE = "MESSAGE";
	private static final String MAIL_TITLE = "TITLE";
	
	@Mock
	Email email;
	
	SimpleMail mail;
	
	@Before
	public void setUp() throws Exception {
		mail = new SimpleMail(email, MAIL_TITLE, MAIL_MESSAGE);
	}

	@Test
	public void setDestinationIsValid() {
		assertEquals(email, mail.getDestination());
	}
	
	@Test
	public void setMessageIsValid() {
		assertEquals(MAIL_MESSAGE, mail.getMessage());
	}

	@Test
	public void setTitleIsValid() {
		assertEquals(MAIL_TITLE, mail.getSubject());
	}

}
