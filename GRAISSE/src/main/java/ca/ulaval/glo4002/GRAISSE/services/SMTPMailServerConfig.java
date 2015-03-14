package ca.ulaval.glo4002.GRAISSE.services;

import java.util.Properties;

import javax.mail.Session;

public class SMTPMailServerConfig {
	private static final String DEFAULT_PORT = "587";
	private final String host;
	private final String user;
	private final String password;
	private String port;
	
	public SMTPMailServerConfig(String host, String user, String password){
		this(host,user,password, DEFAULT_PORT);
	}
	
	public SMTPMailServerConfig(String host, String user, String password, String port){
		this.host = host;
		this.user = user;
		this.password = password;
		this.port = port;
	}
	
	private Properties createPropertiesForSession() {
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", user);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		return properties;
	}
	
	public Session getDefaultSession(){
		Properties properties = createPropertiesForSession();
		return Session.getDefaultInstance(properties);
	}
}
