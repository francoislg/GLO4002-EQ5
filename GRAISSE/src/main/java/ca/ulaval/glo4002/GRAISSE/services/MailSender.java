package ca.ulaval.glo4002.GRAISSE.services;

public interface MailSender {
	public void connect();
	public void send(Mail mail);
	public void disconnect();
}
