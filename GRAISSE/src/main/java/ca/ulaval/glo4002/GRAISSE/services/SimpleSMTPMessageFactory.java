package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleSMTPMessageFactory implements SMTPMessageFactory {
	@Override
	public Message create(Mail mail, Session session) throws MessagingException {
		Message message = new MimeMessage(session);
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinationString()));
		message.setSubject(mail.getSubject());
		message.setText(mail.getMessage());
		
		return message;
	}
}