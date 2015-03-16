package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.boardroom.BookingTrigger;
import ca.ulaval.glo4002.GRAISSE.services.Mail;
import ca.ulaval.glo4002.GRAISSE.services.MailServer;
import ca.ulaval.glo4002.GRAISSE.services.SimpleMail;
import ca.ulaval.glo4002.GRAISSE.user.User;

public class BookingAssignedSendMailTrigger implements BookingTrigger {
	
	private static final String MAIL_SUBJECT = "Update on your booking";
	private static final String BOOKING_IS_ASSIGNED_MESSAGE = "Congratulations ! Assignation succeeded !";
	private static final String BOOKING_IS_NOT_ASSIGNED_MESSAGE = "Assignation failed !";

	private MailServer mailServer;
	private User user;
	private User responsible;
	
	public BookingAssignedSendMailTrigger(MailServer mailServer, User user, User responsible){
		this.mailServer = mailServer;
		this.user = user;
		this.responsible = responsible;
	}
	
	@Override
	public void update(BookingAssignable booking) {
		if(booking.hasCreator(user)){
			sendMailToEmail(booking, user);
			sendMailToEmail(booking, responsible);
		}
	}
	
	private void sendMailToEmail(BookingAssignable booking, User user){
		Mail mailToCreator = new SimpleMail(user.getEmail(), MAIL_SUBJECT, getMailMessage(booking));
		mailServer.sendMail(mailToCreator);
	}
	
	private String getMailMessage(BookingAssignable booking){
		if(booking.isAssigned()){
			return BOOKING_IS_ASSIGNED_MESSAGE;
		}else{
			return BOOKING_IS_NOT_ASSIGNED_MESSAGE;
		}
	}
}