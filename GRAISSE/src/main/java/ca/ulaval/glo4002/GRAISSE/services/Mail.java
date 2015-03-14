package ca.ulaval.glo4002.GRAISSE.services;

public interface Mail {
	public Email getDestination();
	public String getDestinationString();
	public String getSubject();
	public String getMessage();
}
