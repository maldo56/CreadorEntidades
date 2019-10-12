package com.UX.shellmode;

import java.io.File;
import java.util.List;

import com.controller.MainController;
import com.object.DtAttribute;
import com.object.DtEntity;

public class MainShellMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filePath = "/home/maldo56/eclipse-workspace/CreadorEntidades/Resources/Config/Run.xml";
		File file = new File(filePath);  
		
		XmlConverter xmlConverter = new XmlConverter();
		xmlConverter.loadFile(file);
		
		
		String proyectFolder = xmlConverter.getProyectFolder();
		String templateFolder = xmlConverter.getTemplateFolder();
		String packge = xmlConverter.getPackage();
		
		List<DtEntity> entities = xmlConverter.getEntities();
		
		MainController ctrl = new MainController();		
		ctrl.setProyectFolder(proyectFolder);
		ctrl.setTemplateFolder(templateFolder);
		ctrl.setPackge(packge);
		
		ctrl.setObjects(entities);
		
		ctrl.createEntityFiles();
		ctrl.createEntityManagerFiles();
		ctrl.createObjFiles();
		
		ctrl.copyConfigFile();
		
		// Verificación
		
		System.out.println("===   " + proyectFolder);
		System.out.println("===   " + templateFolder);
		System.out.println("===   " + packge);
		
		List<DtAttribute> attributes;
		DtEntity entity = null;
		DtAttribute attribute = null;
		
		for(int x=0; x<entities.size(); x++) {
			entity = entities.get(x);
			
			System.out.println("=============== " + entity.getName() + "  ===============");
			
			attributes = entity.getAttributes();
			
			for(int h=0; h<attributes.size(); h++) {
				attribute = attributes.get(h);
				
				System.out.println(attribute.getType() + " " + attribute.getName());
			}
			
		}
		
	}
	
}
