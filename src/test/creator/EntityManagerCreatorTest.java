package test.creator;

import com.creator.Creator;
import com.creator.EntityManagerCreator;
import com.object.DtAttribute;
import com.object.DtEntity;
import com.object.DtError;

public class EntityManagerCreatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String proyectFolder = "/home/maldo56/Documents/CreatorWorkSpace";
		String templateFolder = "/home/maldo56/eclipse-workspace/CreadorEntidades/Resources/";
		String packge = "com.test.source";

		
		// Creator
		Creator creator = new EntityManagerCreator();
		creator.setProyectFolder(proyectFolder);
		creator.setTemplateFolder(templateFolder);
		creator.setPackge(packge);
		
		
		// Creo entidades
		DtEntity entity1 = new DtEntity("usUaRio");
		
		DtAttribute att1 = new DtAttribute("String", "username");
		DtAttribute att2 = new DtAttribute("String", "password");
		DtAttribute att3 = new DtAttribute("String", "email");
		
		entity1.addAttribute(att1);
		entity1.addAttribute(att2);
		entity1.addAttribute(att3);
		
		
//		===================================================================
		
		
		DtEntity entity2 = new DtEntity("rol");
		
		att1 = new DtAttribute("String", "name");
		att2 = new DtAttribute("int", "permissions");
		
		entity2.addAttribute(att1);
		entity2.addAttribute(att2);
		
		// Agregar al creador y ejecutar
		
		DtError error;
		
		creator.addObject(entity1);
		creator.addObject(entity2);
		
		error = (DtError) creator.execute();
		
		System.out.println("creator.execute();");
		System.out.println("Code: " + error.getCode());
		System.out.println("Message: " + error.getMessage());
		System.out.println("===========================================");
		
	}

}
