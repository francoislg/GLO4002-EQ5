package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;

public class SMTPMailServer implements MailServer {
	private final SMTPMailServerConfig config;
	private Transport transport;
	private SMTPMessageFactory messageFactory;
	
	public SMTPMailServer(SMTPMailServerConfig config, Transport transport, SMTPMessageFactory messageFactory){
		this.config = config;
		this.transport = transport;
		this.messageFactory = messageFactory;
	}
	
	@Override
	public void sendMail(Mail mail) {
		Session session = config.getSession();
		try{
			Message message = messageFactory.create(mail, session);
			transport.sendMessage(message, message.getAllRecipients());
		}catch(MessagingException e){
			throw new CouldNotSendMailException();
		}
	}
}
