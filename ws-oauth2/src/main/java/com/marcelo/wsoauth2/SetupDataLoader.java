package com.marcelo.wsoauth2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.marcelo.wsoauth2.user.User;
import com.marcelo.wsoauth2.user.UserBuilder;
import com.marcelo.wsoauth2.user.UserRepository;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		User joao = new UserBuilder().firstName("João")
								     .lastName("Souza")
								     .email("joao@gmail.com")
								     .build();
		
		User maria = new UserBuilder().firstName("Maria")
								      .lastName("Teixeira")
								      .email("maria@gmail.com")
								      .build();
		
		createUserIfNotFound(joao);
		createUserIfNotFound(maria);
	}
	
	private User createUserIfNotFound(final User user) {
		Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
		
		if(userOptional.isPresent())
			return userOptional.get();
		
		return userRepository.save(user);
	}

}
