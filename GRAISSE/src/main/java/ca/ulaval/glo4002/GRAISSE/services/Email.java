package ca.ulaval.glo4002.GRAISSE.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.InvalidEmailException;

public class Email {
	private final String email;
	// Known regex for email validation
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public Email(String email){
		validateEmail(email);
		this.email = email;
	}
	
	public String getValue(){
		return email;
	}
	
	private void validateEmail(String email){
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()){
			throw new InvalidEmailException();
		};
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
