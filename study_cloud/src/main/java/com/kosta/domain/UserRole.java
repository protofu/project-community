package com.kosta.domain;

public enum UserRole {
	USER("ROLE_USER"),
	MAINTAINER("ROLE_MAINTAINER");
	
	String  role;
	
	UserRole(String role){
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
