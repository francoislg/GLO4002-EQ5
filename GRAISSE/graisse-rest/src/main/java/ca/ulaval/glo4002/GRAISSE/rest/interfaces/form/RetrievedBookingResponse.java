package ca.ulaval.glo4002.GRAISSE.rest.interfaces.form;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingState;

public class RetrievedBookingResponse {
	public final int nombrePersonnes;
	public final String courrielOrganisateur;
	public final BookingState statutDemande;
	public final String salleAssigne;

	public RetrievedBookingResponse(Booking booking) {
		this(booking.getNumberOfSeats(), booking.getPromoterEmail(), booking.getState(), booking.getName());
	}

	public RetrievedBookingResponse(int nombrePersonnes, String courrielOrganisateur, BookingState statutDemande, String salleAssigne) {
		this.nombrePersonnes = nombrePersonnes;
		this.courrielOrganisateur = courrielOrganisateur;
		this.statutDemande = statutDemande;
		this.salleAssigne = salleAssigne;
	}

}
