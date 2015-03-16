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
	private static final String ANOTHER_VALID_EMAIL = "hij@klm.com";
	private static final String MAIL_TITLE = "TITLE";
	private static final String ANOTHER_MAIL_TITLE = "ANOTHER TITLE";
	private static final String MAIL_MESSAGE = "MESSAGE";
	private static final String ANOTHER_MAIL_MESSAGE = "ANOTHER MESSAGE";
	
	@Mock
	Email email;
	
	SimpleMail mail;
	
	@Before
	public void setUp() {
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
	
	@Test
	public void sameSimpleMailAreEquals() {
		assertTrue(mail.equals(mail));
	}
	
	@Test
	public void simpleMailsWithDifferentDestinationsAreNotEquals() {
		Email anotherEmail = mock(Email.class);
		when(anotherEmail.getValue()).thenReturn(ANOTHER_VALID_EMAIL);
		
		SimpleMail mailWithDifferentDestination = new SimpleMail(anotherEmail, MAIL_TITLE, MAIL_MESSAGE);
		
		assertFalse(mail.equals(mailWithDifferentDestination));
	}
	
	@Test
	public void simpleMailsWithDifferentTitlesAreNotEquals() {
		SimpleMail mailWithDifferentTitle = new SimpleMail(email, ANOTHER_MAIL_TITLE, MAIL_MESSAGE);
		assertFalse(mail.equals(mailWithDifferentTitle));
	}
	
	@Test
	public void simpleMailsWithDifferentMessagesAreNotEquals() {	
		SimpleMail mailWithDifferentMessage = new SimpleMail(email, MAIL_TITLE, ANOTHER_MAIL_MESSAGE);
		assertFalse(mail.equals(mailWithDifferentMessage));
	}
	
	@Test
	public void simpleMailsWithEqualsMailTitleAndMessageAreEquals() {
		SimpleMail equalMail = new SimpleMail(email, MAIL_TITLE, MAIL_MESSAGE);
		assertTrue(mail.equals(equalMail));
	}
	
	private void setUpEmailMock(){
		when(email.getValue()).thenReturn(A_VALID_EMAIL);
	}
}