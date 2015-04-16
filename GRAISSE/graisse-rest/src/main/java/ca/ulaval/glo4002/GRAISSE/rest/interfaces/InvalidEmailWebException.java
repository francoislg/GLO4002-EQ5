package ca.ulaval.glo4002.GRAISSE.rest.interfaces;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class InvalidEmailWebException extends WebApplicationException {
	private static final long serialVersionUID = 946700083736492605L;

	public InvalidEmailWebException() {
		super(Response.status(Status.BAD_REQUEST).build());
	}

	public InvalidEmailWebException(String message) {
		super(Response.status(Status.BAD_REQUEST).entity(message).type("text/plain").build());
	}
}
