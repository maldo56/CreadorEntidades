package <Package>;

import <EntityPackage>.<EntityName>;
import <EntityManagerPackage>.<EntityName>EntityManager;

public class Obj<EntityName> {

	private String guid;
<ClassAttributes>
	
	private <EntityName> entity;
	
	private <EntityName>EntityManager entityManager;
	
	
	public Obj<EntityName>() {
		super();
		this.guid = "";

<InitAttributeEmpty>
		this.entity = null;
	}

	// TODO
	public ObjUser(<ConstructorParameters>) {
		super();
		this.guid = "";

<InitConstructorParameters>
		this.entity = null;
	}

	
	// ====================================================================================================
	
	// Entity Manager
	
	public void load(String guid) {
		
		if(this.entityManager == null)
			this.entityManager = new <EntityName>EntityManager();
		
		this.entity = this.entityManager.load(guid);
		
	}
	
	public void insert() {
		
		if(this.entityManager == null)
			this.entityManager = new <EntityName>EntityManager();
		
		<EntityName> entity = new <EntityName>();
<SetEntityAttributes>
		this.entityManager.insert(entity);
	}
	
	public void delete() {
		
		if(this.entityManager == null)
			this.entityManager = new <EntityName>EntityManager();
		
		if(this.entity!=null)
			this.entityManager.delete(this.entity);
		else
			System.out.println("ObjUser - delete(): entity == null");
	}
	
	public void update() {
		
		if(this.entityManager == null)
			this.entityManager = new <EntityName>EntityManager();
		
		if(this.entity!=null) {
			this.entityManager.update(this.entity, this);
		}else {
			System.out.println("ObjUser - update(): entity == null");
		}
		
	}
	
	// ====================================================================================================
	
	// Getters and Setters
	
	public String getGuid() {
		if(this.guid.isEmpty() && this.entity != null)
			return this.entity.getGuid();
		return guid;
	}
<ObjGettersAttributes>
<SettersAttributes>
	public User getEntity() {
		return entity;
	}

}
