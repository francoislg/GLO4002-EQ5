package ca.ulaval.glo4002.GRAISSE.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.InvalidEmailException;

public class Email {
	private final String emailAddress;
	// Known regex for email validation
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public Email(String email) {
		validateEmail(email);
		this.emailAddress = email;
	}

	public String getValue() {
		return emailAddress;
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
		result = prime * result + emailAddress.hashCode();
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
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}
}
