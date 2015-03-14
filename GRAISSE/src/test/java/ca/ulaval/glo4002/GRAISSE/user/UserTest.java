package ca.ulaval.glo4002.GRAISSE.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4002.GRAISSE.user.User;

public class UserTest {
	private static final String VALID_USER_EMAIL = "omg@lol.ca";
	
	User user;
	
	@Before
	public void setUp() {
		user = new User(VALID_USER_EMAIL);
	}

	@Test
	public void userHasEmailWhenGivingSameEmailShouldReturnTrue() {
		boolean result = user.hasEmail(VALID_USER_EMAIL);
		assertTrue(result);
	}
}
