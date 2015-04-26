package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import static org.mockito.Matchers.argThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MailMatcher extends BaseMatcher<MailMessage> {
	private final String email;

	public MailMatcher(String email) {
		this.email = email;
	}

	@Override
	public boolean matches(Object argument) {
		MailMessage mail = (MailMessage) argument;
		return mail.getDestinationString().equals(email) || mail.getAllCarbonCopyRecipients().contains(email);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("destination for mail should contain ").appendText(email);
	}

	public static MailMessage withAMailSentTo(String email) {
		return argThat(new MailMatcher(email));
	}
}