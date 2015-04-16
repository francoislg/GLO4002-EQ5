package ca.ulaval.glo4002.GRAISSE.rest.contexts;

import ca.ulaval.glo4002.GRAISSE.core.user.User;

public class FillerConfig {
	private User mainUser;

	public FillerConfig(User mainUser) {
		this.mainUser = mainUser;
	}

	public User getMainUser() {
		return mainUser;
	}
}
