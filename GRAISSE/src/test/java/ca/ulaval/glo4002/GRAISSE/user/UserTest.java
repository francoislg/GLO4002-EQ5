package ca.ulaval.glo4002.GRAISSE.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.services.Email;
import ca.ulaval.glo4002.GRAISSE.user.User;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	@Mock
	Email email;

	User user;

	@Before
	public void setUp() {
		user = new User(email);
	}

	@Test
	public void givenSameEmailUserHasEmailShouldReturnTrue() {
		boolean result = user.hasEmail(email);
		assertTrue(result);
	}

	@Test
	public void getEmailShouldReturnSameEmail() {
		Email result = user.getEmail();
		assertEquals(email, result);
	}
}