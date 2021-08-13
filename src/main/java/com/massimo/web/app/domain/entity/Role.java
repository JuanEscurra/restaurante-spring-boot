package com.massimo.web.app.domain.entity;


public enum Role {
	ADMINISTRADOR("admin"),
	CAJERO("cajero"),
	MESERO("mesero");
	
	private String name;

    Role(String name) {
		this.name = name;
	}
    
	public String getName() {
		return name;
	}
}
