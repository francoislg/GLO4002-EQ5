package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.SimpleMailMessage;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingAssignedSendMailNotifyer implements Notifyer<BookingAssignable> {

	private static final String MAIL_SUBJECT = "Update on your booking";
	private static final String BOOKING_IS_ASSIGNED_MESSAGE = "Congratulations ! Assignation succeeded !";
	private static final String BOOKING_IS_NOT_ASSIGNED_MESSAGE = "Assignation failed !";

	private MailSender mailSender;
	private User user;
	private User responsible;

	public BookingAssignedSendMailNotifyer(MailSender mailSender, User user, User responsible) {
		this.mailSender = mailSender;
		this.user = user;
		this.responsible = responsible;
	}

	@Override
	public void notify(BookingAssignable booking) {
		if (booking.hasPromoter(user.getEmail())) {
			sendMailToEmail(booking, user);
			sendMailToEmail(booking, responsible);
		}
	}

	private void sendMailToEmail(BookingAssignable booking, User user) {
		MailMessage mailToCreator = new SimpleMailMessage(user.getEmail(), MAIL_SUBJECT, getMailMessage(booking));
		mailSender.sendMail(mailToCreator);
	}

	private String getMailMessage(BookingAssignable booking) {
		if (booking.isAssigned()) {
			return BOOKING_IS_ASSIGNED_MESSAGE;
		} else {
			return BOOKING_IS_NOT_ASSIGNED_MESSAGE;
		}
	}
}