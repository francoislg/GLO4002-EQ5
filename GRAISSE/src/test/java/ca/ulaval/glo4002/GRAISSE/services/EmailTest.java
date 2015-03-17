package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.InvalidEmailException;

@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

	private static final String VALID_EMAIL = "testing@gmail.com";
	private static final String ANOTHER_VALID_EMAIL = "anothertest@gmail.com";
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

	@Test(expected = InvalidEmailException.class)
	public void invalidEmailShouldThrowInvalidEmailException() {
		new Email(INVALID_EMAIL);
	}

	@Test
	public void sameEmailShouldHaveSameHashCode() {
		Email email = new Email(VALID_EMAIL);
		Email sameEmail = new Email(VALID_EMAIL);

		assertEquals(email.hashCode(), sameEmail.hashCode());
	}

	@Test
	public void differentEmailsShouldNotBeEquals() {
		Email email = new Email(VALID_EMAIL);
		Email anotherEmail = new Email(ANOTHER_VALID_EMAIL);

		assertFalse(email.equals(anotherEmail));
	}

	@Test
	public void differentEmailsShouldHaveDifferentHashCode() {
		Email email = new Email(VALID_EMAIL);
		Email anotherEmail = new Email(ANOTHER_VALID_EMAIL);

		assertNotEquals(email.hashCode(), anotherEmail.hashCode());
	}
}