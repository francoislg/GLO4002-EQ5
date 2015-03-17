package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import ca.ulaval.glo4002.GRAISSE.services.exceptions.CouldNotConnectException;

public class SMTPMailSession {
	
	private Session session;
	private String username;
	private String password;
	
	public SMTPMailSession(Session session, String username, String password){
		this.session = session;
		this.username = username;
		this.password = password;
	}
	
	public Transport getSMTPTransport(){
		try {
			return session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			throw new CouldNotConnectException();
		}
	}
	
	public void connect(Transport transport) {
		try {
			transport.connect(username, password);
		} catch (MessagingException e) {
			throw new CouldNotConnectException();
		}
	}

	public Message getNewMessage() {
		return new MimeMessage(session);
	}
}
