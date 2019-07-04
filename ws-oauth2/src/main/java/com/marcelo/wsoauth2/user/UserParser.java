package com.marcelo.wsoauth2.user;

public class UserParser {
	
	private User user;
	
	public UserParser() {
		user = new User();
	}
	
	public UserParser from(UserDTO userDto){
		this.user.setEmail(userDto.getEmail());
		this.user.setFirstName(userDto.getFirstName());
		this.user.setLastName(userDto.getLastName());
		this.user.setPassword(userDto.getPassword());
		this.user.setEnabled(userDto.isEnabled());
		
		return this;
	}
	
	public User build() {
		return this.user;
	}
}
