package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingInMemoryRepository;
import ca.ulaval.glo4002.GRAISSE.persistence.BookingNotFoundException;
import ca.ulaval.glo4002.GRAISSE.rest.contexts.BookingRepositoryFiller;
import ca.ulaval.glo4002.GRAISSE.rest.interfaces.form.RetrievedBookingResponse;

@Path("/demandes")
public class BookingRessource {
	private BookingRepository bookingRepository;

	public BookingRessource() {
		this.bookingRepository = new BookingInMemoryRepository();
		new BookingRepositoryFiller().fill(bookingRepository);
	}

	public BookingRessource(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@GET
	@Path("/{COURRIEL}/{NUMERO_DEMANDE}")
	@Produces(MediaType.APPLICATION_JSON)
	public RetrievedBookingResponse getBooking(@PathParam("COURRIEL") String promoter, @PathParam("NUMERO_DEMANDE") String ID) {
		try {
			Booking foundBooking = bookingRepository.retrieve(new Email(promoter), ID);
			return new RetrievedBookingResponse(foundBooking);
		} catch (BookingNotFoundException exception) {
			throw new BookingNotFoundWebException("Il n'existe pas de demande \"" + ID + "\" pour l'organisateur \"" + promoter + "\"");
		}
	}
}
