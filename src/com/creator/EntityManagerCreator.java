package com.creator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.object.DtEntity;
import com.object.DtError;
import com.util.UtilFileEditor;

public class EntityManagerCreator extends Creator {
	
	
	public EntityManagerCreator() {
		
	}
	

	public String createFile(String template, String packge, Object object) {
		
		String content = "";
		
		if(object instanceof DtEntity) {
		
			DtEntity entity = (DtEntity) object;
			
			// Package
			content = UtilFileEditor.insertPackage(template, packge);
			
			// EntityPackage
			content = UtilFileEditor.insertEntityPackage(content, packge, this.PREFIX_PACKAGE);
			
			// EntityPackage
			content = UtilFileEditor.insertObjPackage(content, packge, this.PREFIX_PACKAGE);
			
			// EntityName
			content = UtilFileEditor.insertEntityName(content, entity.getName());
			
			// Create Update
			content = UtilFileEditor.createUpdateEntityManager(content, entity.getAttributes());
			
		} else {
			content = "Error: ";
		}
		
		return content;
	}
	
	public DtError saveFile(String path, Object object, String content) {
		
		DtError error = new DtError();
		
		if(object instanceof DtEntity) {
			
			DtEntity entity = (DtEntity) object;
		
			try {
				
				File directory = new File(path);
			    if (! directory.exists()){
			        directory.mkdirs();
			    }
			    
			    Path filePath = Paths.get(path + "/" + entity.getName() + "EntityManager.java");
				
				Charset charset = StandardCharsets.UTF_8;
				Files.write(filePath, content.getBytes(charset));
				
				error.setCode("4");
				error.setMessage(this.getClass().getName() + " - saveFile(): Success");
				
			} catch (IOException e) {
				
				error.setCode("5");
				error.setMessage(this.getClass().getName() + " - saveFile(): " + e.getMessage());
			}
		} else {
			error.setCode("6");
			error.setMessage(this.getClass().getName() + " - saveFile(): El ojeto no es compatible con DtEntity");
		}
		
		return error;
	}
	
	public String getTemplateFile(String templateFolder) {
		String aux = templateFolder + "/EntityManagerTemplate.java";
		return aux.replace("//", "/");
	}
	
	public String getRelativeFilePackage() {
		return "EntityManager";
	}

}
