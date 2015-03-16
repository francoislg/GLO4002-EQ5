package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMailTest {
	private static final String A_VALID_EMAIL = "abc@def.com";
	private static final String MAIL_MESSAGE = "MESSAGE";
	private static final String MAIL_TITLE = "TITLE";
	
	@Mock
	Email email;
	
	SimpleMail mail;
	
	@Before
	public void setUp() throws Exception {
		setUpEmailMock();
		mail = new SimpleMail(email, MAIL_TITLE, MAIL_MESSAGE);
	}

	@Test
	public void getDestinationShouldReturnEmailGiven() {
		assertEquals(email, mail.getDestination());
	}
	
	@Test
	public void getDestinationStringShouldReturnEmailAsString() {
		assertEquals(A_VALID_EMAIL, mail.getDestinationString());
	}
	
	@Test
	public void getMessageShouldReturnMessageGiven() {
		assertEquals(MAIL_MESSAGE, mail.getMessage());
	}

	@Test
	public void getSubjectShouldReturnSubjectGiven() {
		assertEquals(MAIL_TITLE, mail.getSubject());
	}
	
	private void setUpEmailMock(){
		when(email.getValue()).thenReturn(A_VALID_EMAIL);
	}
}
