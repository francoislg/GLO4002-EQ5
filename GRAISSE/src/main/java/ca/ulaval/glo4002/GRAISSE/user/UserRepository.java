package ca.ulaval.glo4002.GRAISSE.user;

import ca.ulaval.glo4002.GRAISSE.services.Email;

public interface UserRepository {
	
	public User retrieve(Email email);
	
	public void persist(User user);
}