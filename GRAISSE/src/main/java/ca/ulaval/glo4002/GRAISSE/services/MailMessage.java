package ca.ulaval.glo4002.GRAISSE.services;

public interface MailMessage {
	
	public Email getDestination();
	
	public String getDestinationString();
	
	public String getSubject();
	
	public String getMessage();
}