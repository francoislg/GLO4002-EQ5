package ca.ulaval.glo4002.GRAISSE.persistence;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.core.user.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserInMemoryRepositoryTest {

	@Mock
	User user;

	@Mock
	Email email;

	@Mock
	Email anotherEmail;

	UserInMemoryRepository userInMemoryRepository;

	@Before
	public void setUp() {
		when(user.hasEmail(any(Email.class))).thenReturn(false);
		when(user.hasEmail(email)).thenReturn(true);
		userInMemoryRepository = new UserInMemoryRepository();
	}

	@Test
	public void givenOneUserPersistedWhenRetrievingItShouldReturnSameUser() {
		userInMemoryRepository.persist(user);

		User retrievedUser = userInMemoryRepository.retrieve(email);

		assertEquals(user, retrievedUser);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void givenEmptyRepositoryWhenRetrievingUserNotInMemoryShouldThrowException() {
		userInMemoryRepository.retrieve(email);
	}
}