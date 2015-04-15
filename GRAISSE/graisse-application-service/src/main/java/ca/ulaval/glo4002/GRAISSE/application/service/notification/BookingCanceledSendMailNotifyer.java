package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.SimpleMailMessage;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingCanceledSendMailNotifyer implements Notifyer<BookingAssignable> {

	private static final String MAIL_SUBJECT = "Reservation Anulation";
	private static final String BOOKING_IS_CANCELED = "Booking is cancel";
	private static final String BOOKING_IS_NOT_CANCELED = "Booking in not canceled!";

	private MailSender mailSender;
	private User user;
	private User responsible;
	private List<User> participant;

	public BookingCanceledSendMailNotifyer(MailSender mailSender, User user, User responsible) {
		this.mailSender = mailSender;
		this.user = user;
		this.responsible = responsible;

	}

	public void addParticipant(User participant) {
		this.participant.add(participant);
	}

	@Override
	public void notify(BookingAssignable booking) {
		if (booking.hasCreator(user)) {
			sendMailToEmails(booking, user, participant, responsible);

		}
	}

	private void sendMailToEmails(BookingAssignable booking, User user, List<User> participant, User responsible) {
		MailMessage mail = new SimpleMailMessage(user.getEmail(), MAIL_SUBJECT, getMailMessage(booking));
		for (Email email : createParticipantEmailRepertorie(participant))
			mail.addCarbonCopyRecipient(email);
		mail.addCarbonCopyRecipient(responsible.getEmail());
		mailSender.sendMail(mail);
	}

	private String getMailMessage(BookingAssignable booking) {
		if (booking.isAssigned()) {
			return BOOKING_IS_CANCELED;
		} else {
			return BOOKING_IS_NOT_CANCELED;
		}
	}

	private List<Email> createParticipantEmailRepertorie(List<User> participant) {
		List<Email> participantEmails = new ArrayList<Email>();
		for (User user : participant) {
			participantEmails.add(user.getEmail());
		}
		return participantEmails;

	}

}
