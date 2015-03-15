package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.InvalidEmailException;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {
	private static final String VALID_EMAIL = "testing@gmail.com";
	private static final String INVALID_EMAIL = "notworking";
	
	@Test
	public void validEmailShouldSucceed() {
		Email email = new Email(VALID_EMAIL);
		assertEquals(VALID_EMAIL, email.getValue());
	}
	
	@Test
	public void validEmailShouldBeEqualToSameEmail() {
		Email email = new Email(VALID_EMAIL);
		Email sameEmail = new Email(VALID_EMAIL);
		assertTrue(email.equals(sameEmail));
	}
	
	@Test(expected=InvalidEmailException.class)
	public void invalidEmailShouldThrowInvalidEmailException() {
		Email email = new Email(INVALID_EMAIL);
	}
}
