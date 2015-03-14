package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface MailSender {
	public void send(Message message) throws MessagingException;
}
