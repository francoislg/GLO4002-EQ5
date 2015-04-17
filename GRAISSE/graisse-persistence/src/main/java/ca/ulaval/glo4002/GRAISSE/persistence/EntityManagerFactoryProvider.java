package ca.ulaval.glo4002.GRAISSE.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider {
	
	private static EntityManagerFactory instance;
	
	public static EntityManagerFactory getFactory() {
		if(instance == null) {
			instance = Persistence.createEntityManagerFactory("entity-manager");
		}
		return instance;
	}
}
