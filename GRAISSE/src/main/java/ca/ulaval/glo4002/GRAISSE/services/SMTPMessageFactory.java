package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

public interface SMTPMessageFactory {
	
	public Message create(Mail mail, Session session) throws MessagingException;
}