package ca.ulaval.glo4002.GRAISSE.services;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.mail.Session;

import org.junit.Before;
import org.junit.Test;

public class SMTPSessionFactoryTest {
	private final static String A_HOST = "Host";
	private final static String A_USER = "Superman";
	private final static String A_PASSWORD = "Most secure password ever";
	
	SMTPSessionFactory smtpSessionFactory;
	
	@Before
	public void setUp() throws Exception {
		smtpSessionFactory = new SMTPSessionFactory();
	}

	@Test
	public void createShouldReturnSessionWithPropertyForHostSet() {
		Session session = smtpSessionFactory.create(A_HOST, A_USER, A_PASSWORD);
		assertEquals(A_HOST, session.getProperty("mail.smtp.host"));
	}

	@Test
	public void createShouldReturnSessionWithPropertyForUserSet() {
		Session session = smtpSessionFactory.create(A_HOST, A_USER, A_PASSWORD);
		assertEquals(A_USER, session.getProperty("mail.smtp.user"));
	}
	
	@Test
	public void createShouldReturnSessionWithPropertyForPasswordSet() {
		Session session = smtpSessionFactory.create(A_HOST, A_USER, A_PASSWORD);
		assertEquals(A_PASSWORD, session.getProperty("mail.smtp.password"));
	}
	
	@Test
	public void createShouldReturnSessionWithPropertyForPortSetToDefault() {
		Session session = smtpSessionFactory.create(A_HOST, A_USER, A_PASSWORD);
		assertThat(session.getProperty("mail.smtp.host"), any(String.class));
	}
}
