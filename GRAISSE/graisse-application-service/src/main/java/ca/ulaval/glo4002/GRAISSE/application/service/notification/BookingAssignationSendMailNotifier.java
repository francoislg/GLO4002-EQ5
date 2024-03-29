package ca.ulaval.glo4002.GRAISSE.application.service.notification;

import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailMessage;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.MailSender;
import ca.ulaval.glo4002.GRAISSE.application.service.mailling.SimpleMailMessage;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Notifyer;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class BookingAssignationSendMailNotifier implements Notifyer<AssignedBooking> {

	private static final String MAIL_SUBJECT = "Update on your booking";
	private static final String BOOKING_IS_ASSIGNED_MESSAGE = "Congratulations ! Assignation succeeded !";
	private static final String BOOKING_IS_NOT_ASSIGNED_MESSAGE = "Assignation failed !";

	private MailSender mailSender;
	private User responsible;

	public BookingAssignationSendMailNotifier(MailSender mailSender, User responsible) {
		this.mailSender = mailSender;
		this.responsible = responsible;
	}

	@Override
	public void notify(AssignedBooking booking) {
		MailMessage mail = new SimpleMailMessage(booking.getPromoterEmail(), MAIL_SUBJECT, getMailMessage(booking));
		mail.addCarbonCopyRecipient(responsible.getEmail());
		mailSender.sendMail(mail);
	}

	private String getMailMessage(AssignedBooking booking) {
		if (booking.isAssigned()) {
			return BOOKING_IS_ASSIGNED_MESSAGE;
		} else {
			return BOOKING_IS_NOT_ASSIGNED_MESSAGE;
		}
	}
}