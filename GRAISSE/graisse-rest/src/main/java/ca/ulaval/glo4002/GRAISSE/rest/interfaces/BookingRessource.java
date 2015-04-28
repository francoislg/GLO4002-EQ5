package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.GRAISSE.application.service.booking.Booker;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardrooms;
import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingDTO;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingID;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.booking.Bookings;
import ca.ulaval.glo4002.GRAISSE.core.booking.Priority;
import ca.ulaval.glo4002.GRAISSE.core.reservation.ReservationRepository;
import ca.ulaval.glo4002.GRAISSE.core.reservation.Reservations;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.shared.InvalidEmailException;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.core.user.UserRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BoardroomInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.ReservationInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.UserInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.BookingRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.DemoFillerConfig;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.FillerConfig;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.ReservationsRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.UserRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.AddBookingForm;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.BookingsForEmailResponse;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@Singleton
@Path("/demandes")
public class BookingRessource {
	private Booker booker;
	private Bookings bookings;
	private Reservations reservations;
	private UserRepository userRepository;

	public BookingRessource() {
		BookingRepository bookingRepository = new BookingInMemoryRepository();
		BoardroomRepository boardroomRepository = new BoardroomInMemoryRepository();
		ReservationRepository reservationsRepository = new ReservationInMemoryRepository();
		Boardrooms boardrooms = new Boardrooms(boardroomRepository);

		bookings = new Bookings(bookingRepository);
		reservations = new Reservations(reservationsRepository, boardrooms, bookings);

		booker = new Booker(bookings, reservations);
		userRepository = new UserInMemoryRepository();

		FillerConfig config = DemoFillerConfig.get();

		new BookingRepositoryFiller(config).fill(bookingRepository);
		new ReservationsRepositoryFiller(config).fill(reservationsRepository);
		new UserRepositoryFiller(config).fill(userRepository);
	}

	public BookingRessource(Booker booker, Bookings bookings, Reservations reservations, UserRepository userRepository) {
		this.booker = booker;
		this.bookings = bookings;
		this.reservations = reservations;
		this.userRepository = userRepository;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewBooking(AddBookingForm form) {
		Booking booking = new Booking(getUser(form.courrielOrganisateur), form.nombrePersonne, Priority.integerToPriority(form.priorite));
		booker.addBooking(booking);
		URI targetURIForRedirection;
		try {
			targetURIForRedirection = new URI("/demandes/" + form.courrielOrganisateur + "/" + booking.getID());
		} catch (URISyntaxException e) {
			return Response.serverError().build();
		}
		return Response.seeOther(targetURIForRedirection).build();
	}

	@GET
	@Path("/{COURRIEL}/{NUMERO_DEMANDE}")
	@Produces(MediaType.APPLICATION_JSON)
	public RetrievedBookingResponse getBooking(@PathParam("COURRIEL") String promoter, @PathParam("NUMERO_DEMANDE") String ID) {
		Email email = getEmail(promoter);
		BookingID bookingID = new BookingID(ID);
		if (reservations.hasReservation(email, bookingID)) {
			BookingDTO foundBooking = reservations.retrieveReservationDTO(email, bookingID);
			return new RetrievedBookingResponse(foundBooking);
		} else if (bookings.hasBooking(email, bookingID)) {
			BookingDTO foundBooking = bookings.retrieveDTO(email, bookingID);
			return new RetrievedBookingResponse(foundBooking);
		} else {
			throw new BookingNotFoundWebException("Il n'existe pas de demande \"" + ID + "\" pour l'organisateur \"" + promoter + "\"");
		}
	}

	@GET
	@Path("/{COURRIEL}")
	@Produces(MediaType.APPLICATION_JSON)
	public BookingsForEmailResponse getBookingForEmail(@PathParam("COURRIEL") String promoter) {
		Email email = getEmail(promoter);
		List<BookingDTO> foundBookings = bookings.getUnassignedBookingsWithEmail(email);
		foundBookings.addAll(reservations.getAssignedBookingsWithEmail(email));
		return new BookingsForEmailResponse(foundBookings);
	}

	private Email getEmail(String email) {
		try {
			return new Email(email);
		} catch (InvalidEmailException ex) {
			throw new InvalidEmailWebException("Le courriel \"" + email + "\" n'est pas valide");
		}
	}

	private User getUser(String userEmail) {
		Email email = getEmail(userEmail);
		if (userRepository.exists(email)) {
			return userRepository.retrieve(email);
		} else {
			User user = new User(email);
			userRepository.persist(user);
			return user;
		}
	}
}
