package ca.ulaval.glo4002.GRAISSE.services;

import javax.mail.Session;

public class SMTPMailServerConfig {
	
	private final Session session;
	
	public SMTPMailServerConfig(Session session){
		this.session = session;
	}
	
	public Session getSession(){
		return session;
	}
}