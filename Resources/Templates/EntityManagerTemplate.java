package <Package>;

import <EntityPackage>.<EntityName>;
import <ObjPackage>.Obj<EntityName>;

public class <EntityName>EntityManager extends EntityManager {

	
	public <EntityName>EntityManager() {
		super();
		
	}
	
	public void insert(<EntityName> entity) {	
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}
	
	public void delete(<EntityName> entity) {
		
		entityManager.getTransaction().begin();
		
		if (!entityManager.contains(entity)) {
			entity = entityManager.merge(entity);
		}

		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
	
	public void delete(String guid) {
		
		<EntityName> entity = this.load(guid);
		
		entityManager.getTransaction().begin();
		
		if (!entityManager.contains(entity)) {
			entity = entityManager.merge(entity);
		}

		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}
	
	public void update(<EntityName> entity, Obj<EntityName> obj) {
		
		entityManager.getTransaction().begin();		
<UpdateEntityManager>		
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
	}
	
	public <EntityName> load(String guid) {
		return (<EntityName>) entityManager.find(<EntityName>.class, guid);
	}
}
