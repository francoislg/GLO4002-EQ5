package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;

public class HibernateBookingRepository implements BookingRepository {

	private EntityManager entityManager;

	public HibernateBookingRepository() {
		entityManager = new EntityManagerProvider().getEntityManager();
	}

	public HibernateBookingRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Booking booking) {
		entityManager.persist(booking);
	}

	@Override
	public Collection<Booking> retrieveAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Booking> criteriaQuery = criteriaBuilder.createQuery(Booking.class);
		Root<Booking> booking = criteriaQuery.from(Booking.class);
		criteriaQuery.select(booking);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Collection<Booking> retrieveSortedByPriority() {
		// TODO Auto-generated method stub
		return null;
	}
}
