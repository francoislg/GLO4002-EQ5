package ca.ulaval.glo4002.GRAISSE.core.booking;



public class BookingDTO {
	public final String ID;
	public final int numberOfSeats;
	public final String promoterEmail;
	public final BookingState bookingState;
	public final String boardroomName;
	
	public BookingDTO(String ID, int numberOfSeats, String promoterEmail, BookingState bookingState, String boardroomName) {
		this.ID = ID;
		this.numberOfSeats = numberOfSeats;
		this.promoterEmail = promoterEmail;
		this.bookingState = bookingState;
		this.boardroomName = boardroomName;
	}

	public String getBoardroomName() {
		return boardroomName;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public String getPromoterEmail() {
		return promoterEmail;
	}

	public BookingState getBookingState() {
		return bookingState;
	}
}
