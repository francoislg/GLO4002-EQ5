package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.booking.AssignedBooking;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.Reservation;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationNotFoundException;
import ca.ulaval.glo4002.GRAISSE.core.reservedBoardroom.ReservationsRepository;

public class HibernateReservationRepository implements ReservationsRepository {

	private EntityManager entityManager;

	public HibernateReservationRepository() {
		entityManager = new EntityManagerProvider().getEntityManager();
	}

	public HibernateReservationRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Reservation reservation) {
		entityManager.persist(reservation);
	}

	@Override
	public void remove(Reservation reservation) {
		entityManager.remove(reservation);
	}

	@Override
	public Reservation retrieve(AssignedBooking assignedBooking) throws ReservationNotFoundException {
		return entityManager.find(Reservation.class, assignedBooking);
	}

	@Override
	public Collection<Reservation> retrieveAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
		Root<Reservation> reservation = criteriaQuery.from(Reservation.class);
		criteriaQuery.select(reservation);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public boolean existsWithBoardroom(Boardroom boardroom) {
		Collection<Reservation> reservations = retrieveAll();
		
		for (Reservation reservation : reservations) {
			if (reservation.containsBoardroom(boardroom)) {
				return true;
			}
		}
		return false;
	}
}
