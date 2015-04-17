package ca.ulaval.glo4002.GRAISSE.core.user;

import javax.persistence.Entity;
import javax.persistence.Id;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

@Entity
public class User {

	@Id
	private Email email;

	public User(Email email) {
		this.email = email;
	}

	public boolean hasEmail(Email email) {
		return this.email.equals(email);
	}

	public Email getEmail() {
		return email;
	}
}