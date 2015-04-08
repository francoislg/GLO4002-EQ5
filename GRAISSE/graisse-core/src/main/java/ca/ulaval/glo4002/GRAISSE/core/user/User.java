package ca.ulaval.glo4002.GRAISSE.core.user;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class User {

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