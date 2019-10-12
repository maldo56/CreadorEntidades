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

public class EntityCreator extends Creator {

	
	public EntityCreator() {
		super();
	}


	public String createFile(String template, String packge, Object object) {
		
		String content = "";
		
		if(object instanceof DtEntity) {
		
			DtEntity entity = (DtEntity) object;
		
			// Package
			content = UtilFileEditor.insertPackage(template, packge);
						
			// EntityName
			content = UtilFileEditor.insertClassName(content, entity.getName());
			
			// Create Attributes
			content = UtilFileEditor.insertAttributes(content, entity.getAttributes());
			
			// Create Setters
			content = UtilFileEditor.insertSetters(content, entity.getAttributes());
			
			// Create Getters
			content = UtilFileEditor.insertGetters(content, entity.getAttributes());
	
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
			    
			    Path filePath = Paths.get(path + "/" + entity.getName() + ".java");
				
				Charset charset = StandardCharsets.UTF_8;
				Files.write(filePath, content.getBytes(charset));
				
				
				error.setCode("7");
				error.setMessage(this.getClass().getName() + " - saveFile(): Success");
				
			} catch (IOException e) {
				error.setCode("8");
				error.setMessage(this.getClass().getName() + " - saveFile(): " + e.getMessage());
			}
			
		} else {
			error.setCode("9");
			error.setMessage(this.getClass().getName() + " - saveFile(): El ojeto no es compatible con DtEntity");
		}
		
		return error;
	}
	
	public String getTemplateFile(String templateFolder) {
		String aux = templateFolder + "/EntityTemplate.java";
		return aux.replace("//", "/");
	}

	public String getRelativeFilePackage() {
		return "Entity";
	}
	
}
