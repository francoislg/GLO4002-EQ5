package ca.ulaval.glo4002.GRAISSE.services;

public class SimpleMail implements Mail {

	private final Email destination;
	private final String title;
	private final String message;

	public SimpleMail(Email destination, String title, String message) {
		this.destination = destination;
		this.title = title;
		this.message = message;
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

		SimpleMail other = (SimpleMail) obj;
		return propertiesAreEquals(other);
	}

	private boolean propertiesAreEquals(SimpleMail otherMailToCompareProperties) {
		if (destinationsAreNotEquals(otherMailToCompareProperties)) {
			return false;
		}

		if (titlesAreNotEquals(otherMailToCompareProperties)) {
			return false;
		}

		if (messagesAreNotEquals(otherMailToCompareProperties)) {
			return false;
		}

		return true;
	}

	private boolean destinationsAreNotEquals(SimpleMail otherMailToCompareDestination) {
		return !destination.equals(otherMailToCompareDestination.destination);
	}

	private boolean titlesAreNotEquals(SimpleMail otherMailToCompareTitle) {
		return !title.equals(otherMailToCompareTitle.title);
	}

	private boolean messagesAreNotEquals(SimpleMail otherMailToCompareMessage) {
		return !message.equals(otherMailToCompareMessage.message);
	}
}