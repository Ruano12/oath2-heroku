package com.marcelo.wsoauth2.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.wsoauth2.exceptions.ObjectNotFoundException;
import com.marcelo.wsoauth2.user.User;
import com.marcelo.wsoauth2.user.UserService;

@Service
public class RoleService {
	
	@Autowired
	private UserService userService;
	
	
	public List<Role> findUserRoles(String id){
		Optional<User> user = userService.findUserById(id);
		
		if(!user.isPresent())
			throw new ObjectNotFoundException(String.format("Não encontrado usuario com o id %s", id));
		
		
		return user.get().getRoles();
	}
}
