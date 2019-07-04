package com.marcelo.wsoauth2.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class RoleResource {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role/{id}/user")
	public ResponseEntity<List<Role>> findRoles(@PathVariable String id){
		return ResponseEntity.ok().body(roleService.findUserRoles(id));
	}
}
