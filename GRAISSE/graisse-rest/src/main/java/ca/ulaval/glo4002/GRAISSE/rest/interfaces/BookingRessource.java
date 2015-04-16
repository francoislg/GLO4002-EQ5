package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.InvalidEmailException;
import ca.ulaval.glo4002.GRAISSE.persistence.BoardroomInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.ReservationsInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.BookingRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.DemoFillerConfig;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.FillerConfig;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.ReservationsRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.BookingsForEmailResponse;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@Path("/demandes")
public class BookingRessource {
	private Booker booker;
	private Bookings bookings;
	private Reservations reservations;

	public BookingRessource() {
		BookingRepository bookingRepository = new BookingInMemoryRepository();
		BoardroomRepository boardroomRepository = new BoardroomInMemoryRepository();
		ReservationsRepository reservationsRepository = new ReservationsInMemoryRepository();
		this.reservations = new Reservations(reservationsRepository);
		Boardrooms boardrooms = new Boardrooms(boardroomRepository, reservations);
		this.bookings = new Bookings(bookingRepository, reservations);
		this.booker = new Booker(bookings, boardrooms, reservations);

		FillerConfig config = DemoFillerConfig.get();

		new BookingRepositoryFiller(config).fill(bookingRepository);
		new ReservationsRepositoryFiller(config).fill(reservationsRepository);
	}

	public BookingRessource(Booker booker, Bookings bookings, Reservations reservations) {
		this.booker = booker;
		this.bookings = bookings;
		this.reservations = reservations;
	}

	@GET
	@Path("/{COURRIEL}/{NUMERO_DEMANDE}")
	@Produces(MediaType.APPLICATION_JSON)
	public RetrievedBookingResponse getBooking(@PathParam("COURRIEL") String promoter, @PathParam("NUMERO_DEMANDE") String ID) {
		try {
			BookingDTO foundBooking = reservations.retrieveReservation(getEmail(promoter), new BookingID(ID));
			return new RetrievedBookingResponse(foundBooking);
		} catch (ReservationNotFoundException exception) {
			throw new BookingNotFoundWebException("Il n'existe pas de demande \"" + ID + "\" pour l'organisateur \"" + promoter + "\"");
		}
	}

	@GET
	@Path("/{COURRIEL}")
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsForEmailResponse getBookingForEmail(@PathParam("COURRIEL") String promoter) {
		List<BookingDTO> foundBookings = bookings.getBookingsForEmail(getEmail(promoter));
		return new BookingsForEmailResponse(foundBookings);
	}

	private Email getEmail(String email) {
		try {
			return new Email(email);
		} catch (InvalidEmailException ex) {
			throw new InvalidEmailWebException("Le courriel \"" + email + "\" n'est pas valide");
		}
	}
}
