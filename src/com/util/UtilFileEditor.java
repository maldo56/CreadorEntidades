package com.util;

import java.util.List;

import com.object.DtAttribute;

public class UtilFileEditor {
	
	
	private static final String CLASS_PACKAGE = "<Package>";
	private static final String CLASS_NAME = "<ClassName>";
	
	private static final String ENTITY_PACKAGE = "<EntityPackage>";
	private static final String ENTITY_NAME = "<EntityName>";
	
	private static final String ENTITY_MANAGER_PACKAGE = "<EntityManagerPackage>";
	
	private static final String OBJ_PACKAGE = "<ObjPackage>";
	
	private static final String CLASS_ATTRIBUTES = "<ClassAttributes>";
	private static final String INIT_ATTRIBUTE_EMPTY = "<InitAttributeEmpty>";
	private static final String CLASS_ATTRIBUTES_GETTERS = "<GettersAttributes>";
	private static final String CLASS_ATTRIBUTES_SETTERS = "<SettersAttributes>";
	
	private static final String SET_ENTITY_ATTRIBUTES = "<SetEntityAttributes>";
	
	private static final String OBJ_GETTERS_ATTRIBUTES = "<ObjGettersAttributes>";
	
	private static final String CONSTRUCTOR_PARAMETERS = "<ConstructorParameters>";
	private static final String INIT_CONSTRUCTOR_PARAMETERS = "<InitConstructorParameters>";
	
	private static final String UPDATE_ENTITY_MANAGER = "<UpdateEntityManager>";	

	
	public static String insertPackage(String content, String packge) {
		
		packge = packge.replaceAll("/", ".");
		return content.replaceAll(CLASS_PACKAGE, packge);
	}
	
	public static String insertEntityPackage(String content, String packge, String prefixPackage) {
		int index = packge.indexOf(prefixPackage);
		index = index + prefixPackage.length() + 1;
		packge = packge.substring(0, index) + "Entity";
		
		return content.replaceAll(ENTITY_PACKAGE, packge);
	}
	
	public static String insertEntityManagerPackage(String content, String packge, String prefixPackage) {
		int index = packge.indexOf(prefixPackage);
		index = index + prefixPackage.length() + 1;
		packge = packge.substring(0, index) + "EntityManager";
		
		return content.replaceAll(ENTITY_MANAGER_PACKAGE, packge);
	}
	
	public static String insertObjPackage(String content, String packge, String prefixPackage) {
		int index = packge.indexOf(prefixPackage);
		index = index + prefixPackage.length() + 1;
		packge = packge.substring(0, index) + "Obj";
		
		return content.replaceAll(OBJ_PACKAGE, packge);
	}

	public static String insertClassName(String content, String className) {
		
		return content.replaceAll(CLASS_NAME, className);
	}
	
	public static String insertEntityName(String content, String entityName) {
		
		return content.replaceAll(ENTITY_NAME, entityName);
	}
	
	public static String insertAttributes(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			stringBuilder.append("\n");
			stringBuilder.append("	");
			
			line = "private " + aux.getType() + " " + aux.getName() + ";";
			stringBuilder.append(line);
		}
		
		return content.replaceAll(CLASS_ATTRIBUTES, stringBuilder.toString());
	}
	
	public static String insertGetters(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		String caseAux = "";
		
		stringBuilder = new StringBuilder();
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			caseAux = aux.getName().toLowerCase();
			caseAux = caseAux.substring(0, 1).toUpperCase() + caseAux.substring(1);
			
			line = "	public " + aux.getType() + " get" + caseAux + "() {\n"
				+ "		return this." + aux.getName() + ";\n"
				+ "	}\n\n";
			
			stringBuilder.append(line);
		}
		
		return content.replaceAll(CLASS_ATTRIBUTES_GETTERS, stringBuilder.toString());
	}
	
	public static String insertSetters(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		String caseAux = "";
		
		stringBuilder = new StringBuilder();
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			caseAux = aux.getName().toLowerCase();
			caseAux = caseAux.substring(0, 1).toUpperCase() + caseAux.substring(1);
			
			stringBuilder.append("\n");		
			stringBuilder.append("	");
			
			line = "public void set" + caseAux + "(" + aux.getType() + " " + aux.getName() + ") {";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");		
			
			line = "this." + aux.getName() + " = " + aux.getName() + ";";
			
			stringBuilder.append("		");
			stringBuilder.append(line);
			stringBuilder.append("\n");	
			
			stringBuilder.append("	}\n");
		}

		return content.replaceAll(CLASS_ATTRIBUTES_SETTERS, stringBuilder.toString());
	}
	
	public static String createUpdateEntityManager(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		String caseAux = "";
		
		stringBuilder = new StringBuilder();
		
		for(int x=0; x<attributes.size(); x++) {
			
			aux = attributes.get(x);
			
			caseAux = aux.getName().toLowerCase();
			caseAux = caseAux.substring(0, 1).toUpperCase() + caseAux.substring(1);
			
			stringBuilder.append("\n");			
			stringBuilder.append("		");
			
			switch(aux.getType()) {
			
			case "String": 
				line = "if(!obj.get" + caseAux + "().equals(entity.get"+ caseAux + "()))";
				break;
				
			case "int":
				line = "if(!obj.get" + caseAux + "() == entity.get"+ caseAux + "())";
				break;
			
			case "boolean":
				line = "if(!obj.get" + caseAux + "() == entity.get"+ caseAux + "())";
				break;
			}
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			stringBuilder.append("			");
			
			line = "entity.set" + caseAux + "(obj.get" + caseAux + "());";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
		}
			
		
		return content.replaceAll(UPDATE_ENTITY_MANAGER, stringBuilder.toString());
	}
	
	public static String setEntityAttributes(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		String caseAux = "";
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			caseAux = aux.getName().toLowerCase();
			caseAux = caseAux.substring(0, 1).toUpperCase() + caseAux.substring(1);
			
			stringBuilder.append("		");
			
			line = "entity.set" + caseAux + "(this." + aux.getName() + ");";
			stringBuilder.append(line);
			stringBuilder.append("\n");
		}
		
		return content.replaceAll(SET_ENTITY_ATTRIBUTES, stringBuilder.toString());
	}
	
	public static String initAttributeEmpty(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
						
			switch(aux.getType()) {
			
			case "String": 
				line = "this." + aux.getName() + " = \"\";";
				
				stringBuilder.append("		");
				break;
				
			default:
				line = "";
			}
			
			if (!line.isEmpty()) {
				stringBuilder.append(line);
				stringBuilder.append("\n");
			}
			
		}
		
		return content.replaceAll(INIT_ATTRIBUTE_EMPTY, stringBuilder.toString());
	}
	
	public static String initConstructorParameters(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder sbParameters = new StringBuilder();
		
		String line = "";
		DtAttribute aux;
		
		String parametersLine = "";
		
		
//		String username, String password
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			parametersLine = aux.getType() + " " + aux.getName();
			
			sbParameters.append(parametersLine);
			
			if (!(x == attributes.size() -1)) {
				sbParameters.append(", ");
			}
			
//			this.username = username;
			stringBuilder.append("		");
			
			line = "this." + aux.getName() + " = " + aux.getName() + ";";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			
		}
		
		content = content.replaceAll(CONSTRUCTOR_PARAMETERS, sbParameters.toString());
		return content.replaceAll(INIT_CONSTRUCTOR_PARAMETERS, stringBuilder.toString());
	}
	
	public static String insertObjGetters(String content, List<DtAttribute> attributes) {
		StringBuilder stringBuilder = new StringBuilder();
		String line = "";
		DtAttribute aux;
		
		String caseAux = "";
		
		stringBuilder = new StringBuilder();
		
		for(int x=0; x<attributes.size(); x++) {
			aux = attributes.get(x);
			
			caseAux = aux.getName().toLowerCase();
			caseAux = caseAux.substring(0, 1).toUpperCase() + caseAux.substring(1);
			
			stringBuilder.append("\n");
			stringBuilder.append("	");
			
			line = "public " + aux.getType() + " get" + caseAux + "() {";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			stringBuilder.append("		");
			
			line = "if(this." + aux.getName() + ".isEmpty() && this.entity != null)";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			stringBuilder.append("			");
			
			line = "return this.entity.get" + caseAux + "();";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			stringBuilder.append("		");
			
			line = "return " + aux.getName() + ";";
			
			stringBuilder.append(line);
			stringBuilder.append("\n");
			
			stringBuilder.append("	}\n");
		}
		
		return content.replaceAll(OBJ_GETTERS_ATTRIBUTES, stringBuilder.toString());
	}
	
}
