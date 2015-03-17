package ca.ulaval.glo4002.GRAISSE.services;

public class SMTPMailServer implements MailServer {
	
	private final MailSender mailSender;
	
	public SMTPMailServer(MailSender mailSender){
		this.mailSender = mailSender;
	}
	
	@Override
	public void sendMail(Mail mail) {
		mailSender.connect();
		mailSender.send(mail);
		mailSender.disconnect();
	}
}