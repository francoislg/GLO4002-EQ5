package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;

public class ReservationsRepositoryFiller {
	private FillerConfig config;

	public ReservationsRepositoryFiller(FillerConfig config) {
		this.config = config;
	}

	public void fill(ReservationsRepository repository) {
		for (Reservation reservation : config.getReservations()) {
			repository.persist(reservation);
		}
	}
}
