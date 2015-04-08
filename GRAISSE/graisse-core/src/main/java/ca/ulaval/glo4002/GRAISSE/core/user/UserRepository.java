package ca.ulaval.glo4002.GRAISSE.core.user;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface UserRepository {

	public void persist(User user);

	public User retrieve(Email email);
}