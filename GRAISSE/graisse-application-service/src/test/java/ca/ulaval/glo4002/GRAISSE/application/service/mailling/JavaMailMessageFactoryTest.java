package ca.ulaval.glo4002.GRAISSE.application.service.mailling;

import static org.hamcrest.collection.IsArrayContaining.hasItemInArray;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.exception.CouldNotCreateMessageException;

@RunWith(MockitoJUnitRunner.class)
public class JavaMailMessageFactoryTest {

	private static final String A_MAIL_DESTINATION = "Destination@gmail.com";
	private static final String INVALID_MAIL_DESTINATION = "Destination";
	private static final String ANOTHER_MAIL_DESTINATION = "Another@gmail.com";
	private static final List<String> VALID_CARBONCOPY = new ArrayList<String>(Arrays.asList(ANOTHER_MAIL_DESTINATION));
	private static final String A_MAIL_SUBJECT = "Title";
	private static final String A_MAIL_MESSAGE = "Message";

	@Mock
	MailMessage mail;

	Session session;
	JavaMailMessageFactory javaMailMessageFactory;

	@Before
	public void setUp() {
		setUpMailMock();
		javaMailMessageFactory = new JavaMailMessageFactory();
	}

	@Test
	public void createShouldReturnAMessage() throws MessagingException {
		Message message = javaMailMessageFactory.create(mail, session);
		assertNotNull(message);
	}

	@Test
	public void createShouldReturnAMessageWithRecipientFromMail() throws MessagingException {
		Address expectedAddress = new InternetAddress(A_MAIL_DESTINATION);

		Message message = javaMailMessageFactory.create(mail, session);

		assertThat(message.getAllRecipients(), hasItemInArray(expectedAddress));
	}

	@Test
	public void createShouldAddContentToMessage() throws MessagingException, IOException {
		Message message = javaMailMessageFactory.create(mail, session);
		assertEquals(message.getContent().toString(), A_MAIL_MESSAGE);
	}

	@Test
	public void createShouldAddSubjectToMessage() throws MessagingException, IOException {
		Message message = javaMailMessageFactory.create(mail, session);
		assertEquals(message.getSubject(), A_MAIL_SUBJECT);
	}

	@Test
	public void createShouldAddCarbonCopyToMessage() throws MessagingException, IOException {
		Address expectedAddress = new InternetAddress(ANOTHER_MAIL_DESTINATION);

		Message message = javaMailMessageFactory.create(mail, session);

		assertThat(message.getAllRecipients(), hasItemInArray(expectedAddress));
	}

	@Test(expected = CouldNotCreateMessageException.class)
	public void givenFaultyEmailCreateShouldThrowException() throws MessagingException {
		when(mail.getDestinationString()).thenReturn(INVALID_MAIL_DESTINATION);
		javaMailMessageFactory.create(mail, session);
	}

	private void setUpMailMock() {
		when(mail.getDestinationString()).thenReturn(A_MAIL_DESTINATION);
		when(mail.getSubject()).thenReturn(A_MAIL_SUBJECT);
		when(mail.getAllCarbonCopyRecipients()).thenReturn(VALID_CARBONCOPY);
		when(mail.getMessage()).thenReturn(A_MAIL_MESSAGE);
	}
}