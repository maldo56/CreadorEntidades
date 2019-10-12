package com.object;

import java.util.ArrayList;
import java.util.List;

public class DtEntity {

	private String name;
	private List<DtAttribute> attributes;
	
	public DtEntity() {
		
	}
	
	public DtEntity(String name) {
		super();
		
		name = name.toLowerCase();
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		this.attributes = new ArrayList<DtAttribute>();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name.toLowerCase();
		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public List<DtAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<DtAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(DtAttribute attribute) {
		
		if(this.attributes==null) {
			this.attributes = new ArrayList<DtAttribute>();
		}
		
		this.attributes.add(attribute);
	}
}
