package com.massimo.web.app.domain.entity;

public enum TypeProduct {
	
	BEBIDA("bebida"),
	ENTRADA("entrada"),
	SEGUNDO("segundo"),
	POSTRE("postre");
	
	private String name;
	
	TypeProduct(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
