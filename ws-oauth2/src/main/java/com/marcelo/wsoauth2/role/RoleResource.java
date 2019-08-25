package com.marcelo.wsoauth2.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api")
@Api(description = "Api de CRUD para entidade role")
public class RoleResource {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/role/{id}/user")
	@ApiOperation("Busca roles do usuário.")
	public ResponseEntity<List<Role>> findRoles(@ApiParam("Id do usuário. Não pode ser vazio") @PathVariable String id){
		return ResponseEntity.ok().body(roleService.findUserRoles(id));
	}
}
