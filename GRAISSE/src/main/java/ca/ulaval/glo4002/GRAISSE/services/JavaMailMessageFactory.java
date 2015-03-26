package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotCreateMessageException;

public class JavaMailMessageFactory {

	public Message create(MailMessage mail, Session session) {
		Message message = new MimeMessage(session);
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinationString()));
			message.setSubject(mail.getSubject());
			message.setText(mail.getMessage());

			return message;
		} catch (MessagingException e) {
			throw new CouldNotCreateMessageException();
		}
	}
}