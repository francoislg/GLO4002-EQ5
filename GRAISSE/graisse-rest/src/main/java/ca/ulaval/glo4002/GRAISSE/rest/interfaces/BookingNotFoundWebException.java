package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BookingNotFoundWebException extends WebApplicationException {
	private static final long serialVersionUID = 946700083736492605L;

	public BookingNotFoundWebException() {
		super(Response.status(Status.NOT_FOUND).build());
	}

	public BookingNotFoundWebException(String message) {
		super(Response.status(Status.NOT_FOUND).entity(message).type("text/plain").build());
	}
}
