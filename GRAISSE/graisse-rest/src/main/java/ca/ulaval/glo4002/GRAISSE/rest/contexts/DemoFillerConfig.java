package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class DemoFillerConfig {

	private final static String email = "user@email.ca";

	public static FillerConfig get() {
		User mainUser = new User(new Email(email));

		List<Boardroom> boardrooms = new ArrayList<Boardroom>();

		Boardroom boardroom_plt3904 = new Boardroom("PLT-3904", 100);
		Boardroom boardroom_plt2551 = new Boardroom("PLT-2551", 50);
		Boardroom boardroom_vch2860 = new Boardroom("VCH-2860", 200);

		boardrooms.add(boardroom_plt3904);
		boardrooms.add(boardroom_plt2551);
		boardrooms.add(boardroom_vch2860);

		List<Booking> bookings = new ArrayList<Booking>();

		Booking booking = new Booking(mainUser, 40);

		bookings.add(booking);

		FillerConfig config = new FillerConfig(mainUser, bookings, boardrooms);

		config.addReservation(booking, boardroom_plt2551);

		return config;
	}
}
