package ca.ulaval.glo4002.GRAISSE.rest.interfaces.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;

public class BookingsForEmailResponse {
	private class RetrievedBooking extends RetrievedBookingResponse {
		private final String ID;

		public RetrievedBooking(BookingDTO bookingDTO) {
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
			bookings.add(new RetrievedBooking(booking));
		}
	}

	public List<RetrievedBookingResponse> getBookings() {
		return bookings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingsForEmailResponse other = (BookingsForEmailResponse) obj;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
		return true;
	}
}
