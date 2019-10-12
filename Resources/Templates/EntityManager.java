package <Package>;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityManager {

	private final String PERSISTENCE_UNIT = "Prototipo";
	
	protected static javax.persistence.EntityManager entityManager;
	
	
	protected EntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		this.entityManager = emf.createEntityManager();
	}
	
	
	
}
