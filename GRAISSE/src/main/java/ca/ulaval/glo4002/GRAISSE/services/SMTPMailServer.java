package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotSendMailException;

public class SMTPMailServer implements MailServer {
	private final SMTPMailServerConfig config;
	private MailSender mailSender;
	private MessageFactory messageFactory;
	
	public SMTPMailServer(SMTPMailServerConfig config, MailSender mailSender, MessageFactory messageFactory){
		this.config = config;
		this.mailSender = mailSender;
		this.messageFactory = messageFactory;
	}
	
	@Override
	public void sendMail(Mail mail) {
		Session session = config.getDefaultSession();
		try{
			Message message = messageFactory.create(mail, session);
			mailSender.send(message);
		}catch(MessagingException e){
			throw new CouldNotSendMailException();
		}
	}
}
