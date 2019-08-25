package com.marcelo.wsoauth2.user;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(description = "Api de CRUD para entidade usuário")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	@ApiOperation("Busca todos os usuários.")
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = userService.findAll();
		
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/users/{id}")
	@ApiOperation("Busca usuários pelo id.")
	public ResponseEntity<UserDTO> findById(@ApiParam("Id do usuário. Não pode ser vazio") @PathVariable String id) {
		UserDTO user = userService.findById(id);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/user")
	@ApiOperation("Salva novo usuario.")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDto){
		UserDTO user = userService.create(userDto);
		
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/user")
	@ApiOperation("Altera usuários pelo id.")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDto){
		UserDTO user = userService.alter(userDto);
		
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("/user/{id}")
	@ApiOperation("Deleta usuários pelo id.")
	public ResponseEntity<String> delete(@ApiParam("Id do usuário. Não pode ser vazio") @PathVariable String id){
		userService.delete(id);
		
		return ResponseEntity.ok().body("Usuario deletado com sucesso!!");
	}
	
}