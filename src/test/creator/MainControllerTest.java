package test.creator;

import com.controller.MainController;
import com.object.DtAttribute;
import com.object.DtEntity;

public class MainControllerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String proyectFolder = "/home/maldo56/Documents/CreatorWorkSpace";
		String templateFolder = "/home/maldo56/eclipse-workspace/CreadorEntidades/Resources/Templates";
		String packge = "com.test.source";

		// Configuración
		MainController ctrl = new MainController();		
		ctrl.setProyectFolder(proyectFolder);
		ctrl.setTemplateFolder(templateFolder);
		ctrl.setPackge(packge);
		

		// Creo entidades
		DtEntity entity1 = new DtEntity("usUaRio");
		
		DtAttribute att1 = new DtAttribute("String", "username");
		DtAttribute att2 = new DtAttribute("String", "password");
		DtAttribute att3 = new DtAttribute("String", "email");
		
		entity1.addAttribute(att1);
		entity1.addAttribute(att2);
		entity1.addAttribute(att3);
		
		
//				===================================================================
		
		
		DtEntity entity2 = new DtEntity("rol");
		
		att1 = new DtAttribute("String", "name");
		att2 = new DtAttribute("int", "permissions");
		
		entity2.addAttribute(att1);
		entity2.addAttribute(att2);
		
		// Agregar al creador y ejecutar
		
		ctrl.addObject(entity1);
		ctrl.addObject(entity2);
		
		ctrl.createEntityFiles();
		ctrl.createEntityManagerFiles();
		ctrl.createObjFiles();
		
	}

}
