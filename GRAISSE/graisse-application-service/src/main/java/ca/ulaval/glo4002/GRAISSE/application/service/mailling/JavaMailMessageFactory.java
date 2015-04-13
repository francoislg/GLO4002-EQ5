package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.exception.CouldNotCreateMessageException;

public class JavaMailMessageFactory {

	public Message create(MailMessage mail, Session session) {
		Message message = new MimeMessage(session);
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getDestinationString(), true));
			for(String email : mail.getCC()){
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(email, true));
			}
			message.setSubject(mail.getSubject());
			message.setText(mail.getMessage());

			return message;
		} catch (MessagingException e) {
			throw new CouldNotCreateMessageException();
		}
	}
}