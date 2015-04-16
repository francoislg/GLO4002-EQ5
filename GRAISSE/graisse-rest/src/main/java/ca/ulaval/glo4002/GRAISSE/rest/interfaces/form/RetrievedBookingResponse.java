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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courrielOrganisateur == null) ? 0 : courrielOrganisateur.hashCode());
		result = prime * result + nombrePersonnes;
		result = prime * result + ((salleAssigne == null) ? 0 : salleAssigne.hashCode());
		result = prime * result + ((statutDemande == null) ? 0 : statutDemande.hashCode());
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
		RetrievedBookingResponse other = (RetrievedBookingResponse) obj;
		if (courrielOrganisateur == null) {
			if (other.courrielOrganisateur != null)
				return false;
		} else if (!courrielOrganisateur.equals(other.courrielOrganisateur))
			return false;
		if (nombrePersonnes != other.nombrePersonnes)
			return false;
		if (salleAssigne == null) {
			if (other.salleAssigne != null)
				return false;
		} else if (!salleAssigne.equals(other.salleAssigne))
			return false;
		if (statutDemande != other.statutDemande)
			return false;
		return true;
	}
}
