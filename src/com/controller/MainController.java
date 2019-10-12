package com.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.creator.Creator;
import com.creator.EntityCreator;
import com.creator.EntityManagerCreator;
import com.creator.ObjCreator;
import com.object.DtEntity;
import com.object.DtError;

public class MainController {

	private String proyectFolder = "";
	private String templateFolder = "";
	private String packge = "";
	
	private Creator creator = null;
	private List<Object> objects = null;
	
	
	
	public MainController() {
		
	}
	
	// ======================================================================================
	// ===============================  FUNCTIONS ===========================================
	
	public void addObject(Object object) {
		
		if(this.objects == null) {
			this.objects = new ArrayList<Object>();
		}
		
		this.objects.add(object);
	}
	
	
	public void createEntityFiles() {
		
		creator = new EntityCreator();
		creator.setProyectFolder(proyectFolder);
		creator.setTemplateFolder(templateFolder);
		creator.setPackge(packge);
		creator.setObjects(this.objects);
		
		this.execute();
	}
	
	public void createEntityManagerFiles() {
			
			creator = new EntityManagerCreator();
			creator.setProyectFolder(proyectFolder);
			creator.setTemplateFolder(templateFolder);
			creator.setPackge(packge);
			creator.setObjects(this.objects);
			
			this.execute();
		}

	public void createObjFiles() {
		
		creator = new ObjCreator();
		creator.setProyectFolder(proyectFolder);
		creator.setTemplateFolder(templateFolder);
		creator.setPackge(packge);
		creator.setObjects(this.objects);
		
		this.execute();
	}
	
	public void copyConfigFile() {
		this.copyFile("Resources/Config/.classpath", this.proyectFolder + "/.classpath");
		this.copyFile("Resources/Config/.proyect", this.proyectFolder + "/.proyect");
	}

	// ======================================================================================
	// ==================================== UTILS ===========================================

	private boolean configIsValid() {
		return !(this.proyectFolder.isEmpty() || this.packge.isEmpty() || this.templateFolder.isEmpty());
	}
	
	private DtError execute() {
		
		DtError error;
		
		if(this.configIsValid()) {
			
			creator.setProyectFolder(proyectFolder);
			creator.setTemplateFolder(templateFolder);
			creator.setPackge(packge);
			
			error = (DtError) creator.execute();
			
		} else {
			error = new DtError();
			error.setCode("");
			error.setMessage("Error: No se ha cargado la configuración");
		}
		
		return error;
	}
	
	private void copyFile(String source, String dest) {
		
		try {
			InputStream is = null;
		    OutputStream os = null;
		    try {
		        is = new FileInputStream(source);
		        os = new FileOutputStream(dest);

		        // the size of the buffer doesn't have to be exactly 1024 bytes, try playing around with this number and see what effect it will have on the performance
		        byte[] buffer = new byte[1024];
		        int length = 0;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		    } finally {
		        is.close();
		        os.close();
		    }
		} catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	// ======================================================================================
	// ============================= GETTERS AND SETTERS ====================================

	public String getProyectFolder() {
		return proyectFolder;
	}


	public void setProyectFolder(String proyectFolder) {
		this.proyectFolder = proyectFolder;
	}


	public String getTemplateFolder() {
		return templateFolder;
	}


	public void setTemplateFolder(String templateFolder) {
		this.templateFolder = templateFolder;
	}


	public String getPackge() {
		return packge;
	}


	public void setPackge(String packge) {
		this.packge = packge;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<DtEntity> objects) {
		
		for(int x=0; x<objects.size(); x++) {
			this.addObject(objects.get(x));
		}
	}
		
}
