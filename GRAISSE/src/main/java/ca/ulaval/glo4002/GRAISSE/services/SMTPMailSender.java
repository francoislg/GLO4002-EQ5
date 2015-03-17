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

	public SMTPMailSender(SMTPMessageFactory messageFactory, SMTPMailSession mailSession){
		this.messageFactory = messageFactory;
		this.mailSession = mailSession;
		this.transport = mailSession.getSMTPTransport();
	}
	
	@Override
	public void connect() {
		mailSession.connect(transport);
	}

	@Override
	public void send(Mail mail) {
		try {
			Message message = messageFactory.create(mail, mailSession);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new CouldNotSendMailException();
		}
	}

	@Override
	public void disconnect() {
		try {
			transport.close();
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new CouldNotCloseConnection();
		}
	}

}
