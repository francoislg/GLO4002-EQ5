package ca.ulaval.glo4002.GRAISSE.persistence;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.GRAISSE.core.shared.Email;
import ca.ulaval.glo4002.GRAISSE.core.user.User;
import ca.ulaval.glo4002.GRAISSE.core.user.UserRepository;

public class HibernateUserRepository implements UserRepository {

	private EntityManager entityManager;
	
	public HibernateUserRepository() {
		entityManager = new EntityManagerProvider().getEntityManager();
	}
	
	public HibernateUserRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void persist(User user) {
		entityManager.persist(user);
	}

	@Override
	public User retrieve(Email email) {
		return entityManager.find(User.class, email);
	}
}
