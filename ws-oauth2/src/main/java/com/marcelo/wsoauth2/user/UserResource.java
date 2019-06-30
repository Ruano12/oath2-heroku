package com.marcelo.wsoauth2.user;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = userService.findAll();
		
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		UserDTO user = userService.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDto){
		UserDTO user = userService.create(userDto);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/user")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDto){
		UserDTO user = userService.alter(userDto);
		
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> delete(@PathVariable String id){
		userService.delete(id);
		
		return ResponseEntity.ok().body("Usuario deletado com sucesso!!");
	}
	
}