package ca.ulaval.glo4002.GRAISSE.rest.interfaces.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;

public class BookingsForEmailResponse {
	private class RetrievedBookingDEBUG extends RetrievedBookingResponse {
		private final String ID;

		public RetrievedBookingDEBUG(BookingDTO bookingDTO) {
			super(bookingDTO);
			this.ID = bookingDTO.getID().toString();
		}

		public String getID() {
			return ID;
		}
	}

	private List<RetrievedBookingResponse> bookings;

	public BookingsForEmailResponse(Collection<BookingDTO> bookingDTOList) {
		bookings = new ArrayList<RetrievedBookingResponse>();
		for (BookingDTO booking : bookingDTOList) {
			bookings.add(new RetrievedBookingDEBUG(booking));
		}
	}

	public List<RetrievedBookingResponse> getBookings() {
		return bookings;
	}
}
