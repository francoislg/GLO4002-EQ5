package ca.ulaval.glo4002.GRAISSE.services;

import java.util.Properties;

import javax.mail.Session;

public class SMTPMailSessionFactory {

	private static final String DEFAULT_PORT = "587";

	public SMTPMailSession create(String host, String username, String password) {
		return create(host, username, password, DEFAULT_PORT);
	}

	public SMTPMailSession create(String host, String username, String password, String port) {
		Properties properties = createPropertiesObject(host, port);
		Session session = Session.getInstance(properties);
		return new SMTPMailSession(session, username, password);
	}

	private Properties createPropertiesObject(String host, String port) {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		return properties;
	}
}