package ca.ulaval.glo4002.GRAISSE.core.booking;

public class BookingDTO {
	private final BookingID bookingID;
	private final int numberOfSeats;
	private final String promoterEmail;
	private final BookingState bookingState;
	private final String boardroomName;

	public BookingDTO(BookingID bookingID, int numberOfSeats, String promoterEmail, BookingState bookingState, String boardroomName) {
		this.bookingID = bookingID;
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

	public BookingID getID() {
		return bookingID;
	}
}
