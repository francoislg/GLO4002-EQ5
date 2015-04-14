package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;

@Path("/demandes")
@Produces(MediaType.APPLICATION_JSON)
public class BookingRessource {
	private BookingRepository bookingRepository;
	 
    public BookingRessource(BookingRepository bookingRepository) {
    	this.bookingRepository = bookingRepository;
    }
 
    @GET
    @Path("/{COURRIEL}/{NUMERO_DEMANDE}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<Booking> validateCredentials(@PathParam("COURRIEL") String promoter, @PathParam("NUMERO_DEMANDE") String ID) {
        return this.bookingRepository.retrieveAll();
    }
}
