package ca.ulaval.glo4002.GRAISSE.uat.fixtures;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class FakeBookingFactory {
	
	private static final String A_FAKE_EMAIL = "test@test.com";
	private static final int A_FAKE_NUMBER_OF_SEATS = 5;
	
	public static Booking create() {
       return new Booking(new User(new Email(A_FAKE_EMAIL)), A_FAKE_NUMBER_OF_SEATS);
    }
}
