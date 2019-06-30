package com.marcelo.wsoauth2.user;

public class UserBuilder {
	
	private User user;
	
	public UserBuilder() {
		user = new User();
	}
	
	public UserBuilder id(String id) {
		this.user.setId(id);
		return this;
	}
	
	public UserBuilder firstName(String firstName) {
		this.user.setFirstName(firstName);
		return this;
	}
	
	public UserBuilder lastName(String lastName) {
		this.user.setLastName(lastName);
		return this;
	}
	
	public UserBuilder email(String email) {
		this.user.setEmail(email);
		return this;
	}
	
	public User build() {
		return this.user;
	}
}
