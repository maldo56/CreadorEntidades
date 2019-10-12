package com.accessmanager.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "Usuario", schema = "public")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	private String guid;
	
	private String username;
	private String password;
	private int cantAutos;

	

	public Usuario() {
		super();
		
		UUID uuid = UUID.randomUUID();
		this.guid = uuid.toString();
		
	}
	
	
	// ====================================================================================================
	
	// Getters and Setters

	public void setusername(String username) {
		this.username = username;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public void setcantAutos(int cantAutos) {
		this.cantAutos = cantAutos;
	}


	public String getusername() {
		return this.username;
	}

	public String getpassword() {
		return this.password;
	}

	public int getcantAutos() {
		return this.cantAutos;
	}


}
