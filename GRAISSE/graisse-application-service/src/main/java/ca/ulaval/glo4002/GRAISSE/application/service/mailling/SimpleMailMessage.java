package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public class SimpleMailMessage implements MailMessage {

	private final Email destination;
	private final String title;
	private final String message;
	private List<String> carbonCopyRecipients;

	public SimpleMailMessage(Email destination, String title, String message) {
		this.destination = destination;
		this.title = title;
		this.message = message;
		this.carbonCopyRecipients = new ArrayList<String>();
	}

	@Override
	public String getDestinationString() {
		return destination.getValue();
	}

	@Override
	public Email getDestination() {
		return destination;
	}

	@Override
	public String getSubject() {
		return title;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		SimpleMailMessage other = (SimpleMailMessage) obj;
		return propertiesAreEquals(other);
	}

	private boolean propertiesAreEquals(SimpleMailMessage otherMailToCompareProperties) {
		if (!destinationsAreEquals(otherMailToCompareProperties)) {
			return false;
		}

		if (!titlesAreEquals(otherMailToCompareProperties)) {
			return false;
		}

		if (!messagesAreEquals(otherMailToCompareProperties)) {
			return false;
		}

		return true;
	}

	private boolean destinationsAreEquals(SimpleMailMessage otherMailToCompareDestination) {
		return destination.equals(otherMailToCompareDestination.destination);
	}

	private boolean titlesAreEquals(SimpleMailMessage otherMailToCompareTitle) {
		return title.equals(otherMailToCompareTitle.title);
	}

	private boolean messagesAreEquals(SimpleMailMessage otherMailToCompareMessage) {
		return message.equals(otherMailToCompareMessage.message);
	}

	@Override
	public List<String> getAllCarbonCopyRecipients() {
		return carbonCopyRecipients;
	}

	@Override
	public void addCarbonCopyRecipient(Email email) {
		carbonCopyRecipients.add(email.getValue());
	}
}