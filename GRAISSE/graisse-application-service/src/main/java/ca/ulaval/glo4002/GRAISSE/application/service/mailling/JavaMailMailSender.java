package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.exception.CouldNotCloseConnectionException;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.exception.CouldNotConnectException;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.exception.CouldNotSendMailException;

public class JavaMailMailSender implements MailSender {

	private Session session;
	private Transport transport;
	private JavaMailMessageFactory messageFactory;
	private String username;
	private String password;

	public JavaMailMailSender(JavaMailMessageFactory messageFactory, Session session, String username, String password) {
		this.messageFactory = messageFactory;
		this.session = session;
		try {
			this.transport = session.getTransport();
		} catch (NoSuchProviderException e) {
			throw new CouldNotConnectException();
		}
		this.username = username;
		this.password = password;
	}

	@Override
	public void sendMail(MailMessage mail) {
		connect();
		try {
			Message message = messageFactory.create(mail, session);
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			throw new CouldNotSendMailException();
		} finally {
			disconnect();
		}
	}

	private void connect() {
		try {
			transport.connect(username, password);
		} catch (MessagingException e) {
			throw new CouldNotConnectException();
		}
	}

	private void disconnect() {
		try {
			transport.close();
		} catch (MessagingException e) {
			throw new CouldNotCloseConnectionException();
		}
	}
}
