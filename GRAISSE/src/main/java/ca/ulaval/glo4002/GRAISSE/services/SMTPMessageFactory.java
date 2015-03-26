package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;

public interface SMTPMessageFactory {
	
	public Message create(Mail mail, SMTPMailSession session);
}