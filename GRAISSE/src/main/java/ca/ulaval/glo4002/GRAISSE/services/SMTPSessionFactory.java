package ca.ulaval.glo4002.GRAISSE.services;

import java.util.Properties;

import javax.mail.Session;

public class SMTPSessionFactory {
	
	private static final String DEFAULT_PORT = "587";
	
	public Session create(String host, String user, String password){
		return create(host, user, password, DEFAULT_PORT);
	}
	
	public Session create(String host, String user, String password, String port) {
		Properties properties = createPropertiesObject(host, user, password, port);
		return Session.getInstance(properties);
	}
	
	private Properties createPropertiesObject(String host, String user, String password, String port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", user);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		return properties;
	}
}