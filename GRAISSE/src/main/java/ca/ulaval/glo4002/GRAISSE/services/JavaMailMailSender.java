package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class JavaMailMailSender implements MailSender {

	@Override
	public void send(Message message) throws MessagingException {
		Transport.send(message);
	}
}
