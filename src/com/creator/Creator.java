package com.creator;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.object.DtError;

public abstract class Creator {

	protected final String PREFIX_PACKAGE = "domain";
	
	protected String proyectFolder = "";
	protected String templateFolder = "";
	protected String packge = "";
	
	protected List<Object> objects = null;
	private String templateContent = "";
	
	
	// ======================================================================================
	// ============================ ABSTRACT FUNCTIONS ======================================
	
	protected abstract String getTemplateFile(String templateFolder);
	protected abstract String getRelativeFilePackage();
	protected abstract String createFile(String template, String packge, Object object);
	protected abstract DtError saveFile(String path, Object object, String content);
	
	
	// ======================================================================================
	// ===============================  FUNCTIONS ===========================================
	
	public void addObject(Object object) {
		
		if(this.objects == null) {
			this.objects = new ArrayList<Object>();
		}
		
		this.objects.add(object);
	}
	

	public DtError execute() {
			
		DtError error = new DtError();
		
		this.templateContent = this.loadTemplate(getTemplateFile(this.templateFolder));
		
		if(this.objects!=null) {
			
			if(!this.templateContent.isEmpty()) {
				Iterator<Object> it = this.objects.iterator();
				Object aux;
				String contentNewFile = "";
				
				String pkg = this.packge.replace("/", ".") + "." + this.PREFIX_PACKAGE + "." + getRelativeFilePackage();
				pkg = pkg.replace("//", "/");
				pkg = pkg.replace("\\.\\.", ".");
				
				String pathNewFile = this.getProyectFolder() + "/src/" + pkg.replaceAll("\\.", "/") + "/";
				
				while(it.hasNext()) {
					aux = it.next();
					contentNewFile = this.templateContent;
					contentNewFile = this.createFile(contentNewFile, pkg, aux);
					this.saveFile(pathNewFile, aux, contentNewFile);
				}
				
				error.setCode("1");
				error.setMessage(this.getClass().getName() + " - execute(): Success");
				
			} else {
				
				error.setCode("2");
				error.setMessage(this.getClass().getName() + " - execute(): Error: Template no encontrado");
			}
			
		} else {
			// TODO
			
			error.setCode("3");
			error.setMessage(this.getClass().getName() + " - execute(): Error: No hay entidades");
		}
		
		return error;
	}
	
	// ======================================================================================
	// ==================================== UTILS ===========================================

	protected String loadTemplate(String path) {
		Path filePath = Paths.get(path);
		Charset charset = StandardCharsets.UTF_8;
		
		String content = "";
		
		try {
			content = new String(Files.readAllBytes(filePath), charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	// ======================================================================================
	// ============================= GETTERS AND SETTERS ====================================
	
	public String getProyectFolder() {
		return proyectFolder;
	}

	public void setProyectFolder(String proyectFolder) {
		this.proyectFolder = proyectFolder.replace("//", "/");
	}
	
	public String getPackge() {
		return packge;
	}
	
	public void setPackge(String packge) {
		packge = packge.replaceAll("/", ".");
		this.packge = packge;
	}
	
	public String getTemplateFolder() {
		return templateFolder;
	}
	
	public void setTemplateFolder(String templateFolder) {
		this.templateFolder = templateFolder.replace("//", "/");
	}
	
	public List<Object> getObjects() {
		return objects;
	}
	
	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	
	public String getPREFIX_PACKAGE() {
		return PREFIX_PACKAGE;
	}
}
