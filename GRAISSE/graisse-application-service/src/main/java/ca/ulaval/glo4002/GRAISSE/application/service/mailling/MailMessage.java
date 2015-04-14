package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

public interface MailMessage {
	
	public Email getDestination();
	
	public String getDestinationString();
	
	public void addCarbonCopyRecipient(Email email);

	public List<String> getAllCarbonCopyRecipients();
	
	public String getSubject();
	
	public String getMessage();
}