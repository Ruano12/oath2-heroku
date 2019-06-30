package com.marcelo.wsoauth2.role;

public class RoleBuilder {
	
	Role role;
	
	public RoleBuilder(String name) {
		this.role = new Role();
		this.role.setName(name);
	}
	
	private Role build() {
		return this.role;
	}
}
