package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface SMTPMessageFactory {
	
	public Message create(Mail mail, SMTPMailSession session) throws MessagingException;
}