package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.user.UserRepository;

public class UserRepositoryFiller {
	private FillerConfig config;

	public UserRepositoryFiller(FillerConfig config) {
		this.config = config;
	}

	public void fill(UserRepository repository) {
		repository.persist(config.getMainUser());
	}
}
