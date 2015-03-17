package ca.ulaval.glo4002.GRAISSE.services;

import static org.mockito.Mockito.verify;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailServerTest {

	@Mock
	Mail mail;

	@Mock
	MailSender mailSender;

	SMTPMailServer smtpMailServer;

	@Before
	public void setUp() throws Exception {
		smtpMailServer = new SMTPMailServer(mailSender);
	}

	@Test
	public void sendMailShouldCallTransportSendMessage() throws MessagingException {
		smtpMailServer.sendMail(mail);
		verify(mailSender).send(mail);
	}
}