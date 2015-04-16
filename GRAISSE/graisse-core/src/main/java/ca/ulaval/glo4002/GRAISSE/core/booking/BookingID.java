package ca.ulaval.glo4002.GRAISSE.core.booking;

import java.util.UUID;

public class BookingID {
	private final String bookingID;

	public BookingID() {
		this(UUID.randomUUID().toString());
	}

	public BookingID(String bookingID) {
		this.bookingID = bookingID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingID == null) ? 0 : bookingID.hashCode());
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
		BookingID other = (BookingID) obj;
		if (bookingID == null) {
			if (other.bookingID != null)
				return false;
		} else if (!bookingID.equals(other.bookingID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bookingID;
	}
}
