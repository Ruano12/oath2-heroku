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
	
}
