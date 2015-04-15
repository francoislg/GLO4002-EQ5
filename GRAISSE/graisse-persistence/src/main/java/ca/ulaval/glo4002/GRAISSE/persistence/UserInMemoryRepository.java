package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.core.user.UserNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.user.UserRepository;

public class UserInMemoryRepository implements UserRepository {

	private List<User> users;

	public UserInMemoryRepository() {
		users = new ArrayList<User>();
	}

	@Override
	public void persist(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
	}

	@Override
	public User retrieve(Email email) {
		for (User user : users) {
			if (user.hasEmail(email)) {
				return user;
			}
		}
		throw new UserNotFoundException();
	}
}