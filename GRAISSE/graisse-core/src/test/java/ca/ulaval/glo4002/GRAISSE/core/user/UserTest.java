package ca.ulaval.glo4002.GRAISSE.core.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

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