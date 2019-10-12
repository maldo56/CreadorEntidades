package com.object;

public class DtAttribute {

	private String type;
	private String name;
	
	public DtAttribute() {
		
	}
	
	public DtAttribute(String type, String name) {
		super();
		this.type = type;
		this.name = name.toLowerCase();;
	}
	
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
