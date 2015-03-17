package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotCloseConnection;
import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;

public class SMTPMailSender implements MailSender {

	private SMTPMailSession mailSession;
	private Transport transport;
	private SMTPMessageFactory messageFactory;

	public SMTPMailSender(SMTPMessageFactory messageFactory, SMTPMailSession mailSession) {
		this.messageFactory = messageFactory;
		this.mailSession = mailSession;
		this.transport = mailSession.getSMTPTransport();
	}

	@Override
	public void send(Mail mail) {
		connect();
		try {
			Message message = messageFactory.create(mail, mailSession);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			throw new CouldNotSendMailException();
		}
		disconnect();
	}

	private void connect() {
		mailSession.connect(transport);
	}

	private void disconnect() {
		try {
			transport.close();
		} catch (MessagingException e) {
			throw new CouldNotCloseConnection();
		}
	}

}
