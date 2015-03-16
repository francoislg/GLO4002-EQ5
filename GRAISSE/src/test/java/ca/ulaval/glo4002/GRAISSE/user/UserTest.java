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
	User user;
	
	@Mock
	Email email;
	
	@Before
	public void setUp() {
		user = new User(email);
	}

	@Test
	public void userHasEmailWhenGivingSameEmailShouldReturnTrue() {
		boolean result = user.hasEmail(email);
		assertTrue(result);
	}
	
	@Test
	public void getEmailShouldReturnSameEmail() {
		Email result = user.getEmail();
		assertEquals(email, result);
	}
}
