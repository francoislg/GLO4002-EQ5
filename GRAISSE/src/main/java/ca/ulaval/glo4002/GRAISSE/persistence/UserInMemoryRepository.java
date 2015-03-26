package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.services.Email;
import ca.ulaval.glo4002.GRAISSE.user.User;
import ca.ulaval.glo4002.GRAISSE.user.UserRepository;
import ca.ulaval.glo4002.GRAISSE.user.exceptions.UserNotFoundException;

public class UserInMemoryRepository implements UserRepository {

	private List<User> users;

	public UserInMemoryRepository() {
		users = new ArrayList<User>();
	}

	@Override
	public void persist(User user) {
		if (isNotAlreadyInMemory(user)) {
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

	private boolean isNotAlreadyInMemory(User user) {
		return !users.contains(user);
	}
}