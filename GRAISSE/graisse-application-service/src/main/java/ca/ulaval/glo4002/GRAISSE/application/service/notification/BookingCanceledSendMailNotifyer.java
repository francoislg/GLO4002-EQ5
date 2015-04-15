package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.SimpleMailMessage;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingCanceledSendMailNotifyer implements Notifyer<BookingAssignable> {

	private static final String MAIL_SUBJECT = "Reservation Anulation";
	private static final String MAIL_MESSAGE = "Booking is cancelled";

	private MailSender mailSender;
	private User user;
	private User responsible;

	public BookingCanceledSendMailNotifyer(MailSender mailSender, User user, User responsible) {
		this.mailSender = mailSender;
		this.user = user;
		this.responsible = responsible;

	}

	@Override
	public void notify(BookingAssignable booking) {
		if (booking.hasCreator(user)) {
			MailMessage mail = new SimpleMailMessage(user.getEmail(), MAIL_SUBJECT, MAIL_MESSAGE);
			for (Email email : booking.getParticipantsEmail())
				mail.addCarbonCopyRecipient(email);
			mail.addCarbonCopyRecipient(responsible.getEmail());
			mailSender.sendMail(mail);
		}
	}
}
