package ca.ulaval.glo4002.GRAISSE.services;

import static org.mockito.Mockito.when;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SMTPMailSenderTest {

	private final static String username = "USERNAME";
	private final static String password = "PASSWORD";
	
	@Mock
	JavaMailMessageFactory smtpMessageFactory;

	Session session;

	@Mock
	MailMessage mail;

	@Mock
	Message message;

	Address[] addresses;
	JavaMailMailSender smtpMailSender;

	@Before
	public void setUp() {
		setUpSMTPMessageFactory();
		smtpMailSender = new JavaMailMailSender(smtpMessageFactory, session, username, password);
	}


	private void setUpSMTPMessageFactory() {
		when(smtpMessageFactory.create(mail, session)).thenReturn(message);
		try {
			when(message.getAllRecipients()).thenReturn(addresses);
		} catch (MessagingException e) {
		}
	}
}
