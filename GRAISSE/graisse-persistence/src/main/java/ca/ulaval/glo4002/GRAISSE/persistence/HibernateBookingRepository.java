package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ca.ulaval.glo4002.GRAISSE.core.booking.Booking;
import ca.ulaval.glo4002.GRAISSE.core.booking.BookingRepository;
import ca.ulaval.glo4002.GRAISSE.core.shared.Email;

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
		if(!entityManager.contains(booking)) {
			entityManager.persist(booking);
		}
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
		Comparator<Booking> byPriorityValue = (booking1, booking2) -> booking1.comparePriorityToBooking(booking2);
		return getAssignableBookings().stream().sorted(byPriorityValue).collect(Collectors.toList());
	}

	@Override
	public Collection<Booking> getAssignableBookings() {
		Collection<Booking> bookings = retrieveAll();
		for (Iterator<Booking> bookingIter = bookings.iterator(); bookingIter.hasNext();) {
			Booking booking = bookingIter.next();
			if (!booking.isAssignable()) {
				bookingIter.remove();
			}
		}
		return bookings;
	}

	@Override
	public Booking retrieveBooking(Email promoter, String name) {
		Collection<Booking> bookings = retrieveAll();
		for(Booking booking : bookings){
			if(booking.hasPromoter(promoter) && booking.hasName(name)){
				return booking;
			}
		}
		throw new BookingNotFoundException();
	}
}
