package ca.ulaval.glo4002.GRAISSE.booking;

import ca.ulaval.glo4002.GRAISSE.boardroom.BookingAssignable;
import ca.ulaval.glo4002.GRAISSE.services.Mail;
import ca.ulaval.glo4002.GRAISSE.services.MailServer;
import ca.ulaval.glo4002.GRAISSE.services.SimpleMail;
import ca.ulaval.glo4002.GRAISSE.user.User;

public class BookingAssignedSendMailTrigger implements BookingAssignedTrigger {
	private String mailSubject = "Update on your booking";

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
		Mail mailToCreator = new SimpleMail(user.getEmail(), mailSubject, getMailMessage(booking));
		mailServer.sendMail(mailToCreator);
	}
	
	private String getMailMessage(BookingAssignable booking){
		if(booking.isAssigned()){
			return "Congratulations ! Assignation succeeded !";
		}else{
			return "Assignation failed !";
		}
	}
}
