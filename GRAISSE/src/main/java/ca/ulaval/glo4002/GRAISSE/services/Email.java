package ca.ulaval.glo4002.GRAISSE.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.InvalidEmailException;

public class Email {
	private final String emailAdresse;
	// Known regex for email validation
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public Email(String email) {
		validateEmail(email);
		this.emailAdresse = email;
	}

	public String getValue() {
		return emailAdresse;
	}

	private void validateEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new InvalidEmailException();
		}
		;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + emailAdresse.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (emailAdresse == null) {
			if (other.emailAdresse != null)
				return false;
		} else if (!emailAdresse.equals(other.emailAdresse))
			return false;
		return true;
	}
}
