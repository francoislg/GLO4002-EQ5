package ca.ulaval.glo4002.GRAISSE.rest.interfaces.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;

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

	private Map<String, List<RetrievedBookingResponse>> bookings;

	public BookingsForEmailResponse(Collection<BookingDTO> bookingDTOList) {
		bookings = new HashMap<String, List<RetrievedBookingResponse>>();
		bookings.put("acceptees", new ArrayList<RetrievedBookingResponse>());
		for (BookingDTO booking : bookingDTOList) {
			if (booking.getBookingState().equals(BookingState.ASSIGNED)) {
				bookings.get("acceptees").add(new RetrievedBooking(booking));
			} else {
				bookings.get("autres").add(new RetrievedBooking(booking));
			}
		}
	}

	public Map<String, List<RetrievedBookingResponse>> getBookings() {
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
