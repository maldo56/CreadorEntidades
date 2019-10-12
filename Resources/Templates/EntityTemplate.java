package <Package>;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "<ClassName>", schema = "public")
public class <ClassName> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String guid;
<ClassAttributes>

	public <ClassName>() {
		super();
		
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		
	}
	
	
	// ====================================================================================================
	
	// Getters and Setters

<SettersAttributes>
<GettersAttributes>
}
