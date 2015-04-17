package ca.ulaval.glo4002.GRAISSE.persistence;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ca.ulaval.glo4002.GRAISSE.core.boardroom.Boardroom;
import ca.ulaval.glo4002.GRAISSE.core.boardroom.BoardroomRepository;

public class HibernateBoardroomRepository implements BoardroomRepository {

	private EntityManager entityManager;

	public HibernateBoardroomRepository() {
		entityManager = new EntityManagerProvider().getEntityManager();
	}

	public HibernateBoardroomRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void persist(Boardroom boardroom) {
		entityManager.persist(boardroom);
	}

	@Override
	public Boardroom retrieve(String boardroomName) {
		return entityManager.find(Boardroom.class, boardroomName);
	}

	@Override
	public Collection<Boardroom> retrieveAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Boardroom> criteriaQuery = criteriaBuilder.createQuery(Boardroom.class);
		Root<Boardroom> boardroom = criteriaQuery.from(Boardroom.class);
		criteriaQuery.select(boardroom);
	
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public Collection<Boardroom> retrieveBoardroomsOrderedByNumberOfSeats() {
		Comparator<Boardroom> byNumberOfSeats = (boardroom1, boardroom2) -> boardroom1.compareByNumberOfSeats(boardroom2);
		return retrieveAll().stream().sorted(byNumberOfSeats).collect(Collectors.toList());
	}
}
