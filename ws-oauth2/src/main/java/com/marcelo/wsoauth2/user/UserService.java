package com.marcelo.wsoauth2.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.wsoauth2.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll(){
		List<User> user = userRepository.findAll();
		return user.stream().map(x -> new UserDTO().builder(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		Optional<User> user = userRepository.findById(id);
		
		return new UserDTO().builder(user.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado")));
	}
	
	public UserDTO create(UserDTO userDto) {
		User user = new UserBuilder().firstName(userDto.getFirstName())
									 .lastName(userDto.getLastName())
									 .email(userDto.getEmail())
									 .build();
		return new UserDTO().builder(userRepository.save(user));
	}
	
	public UserDTO alter(UserDTO userDto) {
		Optional<User> user = userRepository.findById(userDto.getId());
		
		if(!user.isPresent())
			throw new ObjectNotFoundException(String.format("Usuario não encontrado com o id %s", userDto.getId()));
		
		user.get().setEmail(userDto.getEmail());
		user.get().setFirstName(userDto.getFirstName());
		user.get().setLastName(userDto.getLastName());
		
		return new UserDTO().builder(userRepository.save(user.get()));
	}
	
	public void delete(String id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new ObjectNotFoundException(String.format("Usuario não encontrado com o id %s", id));
		
		userRepository.deleteById(id);
	}
	
}
