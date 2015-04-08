package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface MailMessage {
	
	public Email getDestination();
	
	public String getDestinationString();
	
	public String getSubject();
	
	public String getMessage();
}