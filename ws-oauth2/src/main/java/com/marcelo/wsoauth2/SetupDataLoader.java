package com.marcelo.wsoauth2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.marcelo.wsoauth2.role.Role;
import com.marcelo.wsoauth2.role.RoleRepository;
import com.marcelo.wsoauth2.user.User;
import com.marcelo.wsoauth2.user.UserBuilder;
import com.marcelo.wsoauth2.user.UserRepository;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private String roleAdmin = "ROLE_ADMIN";
	private String roleUser = "ROLE_USER";
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		User joao = new UserBuilder().firstName("Jo�o")
								     .lastName("Souza")
								     .email("joao@gmail.com")
								     .build();
		
		User maria = new UserBuilder().firstName("Maria")
								      .lastName("Teixeira")
								      .email("maria@gmail.com")
								      .build();
		
		createRoleIfNotFound(roleAdmin);
		createRoleIfNotFound(roleAdmin);
		
		createUserIfNotFound(joao);
		createUserIfNotFound(maria);
	}
	
	private User createUserIfNotFound(final User user) {
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
		
		if(userOptional.isPresent())
			return userOptional.get();
		
		return userRepository.save(user);
	}

	private Role createRoleIfNotFound(String name) {
		Optional<Role> rolerOptional = roleRepository.findByName(name);
		
		if(rolerOptional.isPresent())
			return rolerOptional.get();
		
		Role role = new Role();
		role.setName(name);
		
		return roleRepository.save(role);
	}

}
