package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;

import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SMTPSessionFactoryTest {

	private static final String A_HOST = "Host";
	private static final String A_USER = "Superman";
	private static final String A_PASSWORD = "Most secure password ever";

	@Mock
	Transport transport;

	SMTPMailSessionFactory smtpSessionFactory;

	@Before
	public void setUp() throws Exception {
		smtpSessionFactory = new SMTPMailSessionFactory();
	}

	@Test
	public void createShouldReturnASession() {
		SMTPMailSession mailSession = smtpSessionFactory.create(A_HOST, A_USER, A_PASSWORD);
		assertNotNull(mailSession);
	}
}