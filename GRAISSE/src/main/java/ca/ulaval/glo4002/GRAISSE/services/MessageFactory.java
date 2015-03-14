package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

public interface MessageFactory {

	public abstract Message create(Mail mail, Session session) throws MessagingException;

}