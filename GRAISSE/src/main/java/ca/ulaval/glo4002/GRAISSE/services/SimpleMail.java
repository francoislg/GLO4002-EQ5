package ca.ulaval.glo4002.GRAISSE.services;

public class SimpleMail implements Mail {
	private final Email destination;
	private final String title;
	private final String message;
	
	public SimpleMail(Email destination, String title, String message){
		this.destination = destination;
		this.title = title;
		this.message = message;
	}

	@Override
	public String getDestinationString() {
		return destination.getValue();
	}
	
	@Override
	public Email getDestination(){
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
}
