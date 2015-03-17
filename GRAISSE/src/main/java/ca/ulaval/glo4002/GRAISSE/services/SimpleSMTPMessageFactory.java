package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public class SimpleSMTPMessageFactory implements SMTPMessageFactory {
	
	@Override
	public Message create(Mail mail, SMTPMailSession session) throws MessagingException {
		Message message = session.getNewMessage();
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinationString()));
		message.setSubject(mail.getSubject());
		message.setText(mail.getMessage());
		
		return message;
	}
}