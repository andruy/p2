package com.revature.controllers;

import java.util.List;

import io.micrometer.core.annotation.Timed;
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

import com.revature.annotations.Authorized;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.AuthorizationService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthorizationService authorizationService;

	@Authorized(allowedRoles = {Role.ADMIN})
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@Timed(value = "get.id.time") //custom metric
	@GetMapping("/{id}")
	@Authorized(allowedRoles = {Role.ADMIN})
	public ResponseEntity<User> findById(@PathVariable("id") int id) {
		authorizationService.guardByUserId(id);
		
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user) {
		
		return ResponseEntity.accepted().body(userService.insert(user));
	}
	
	
	@PutMapping
	@Authorized(allowedRoles = {Role.ADMIN, Role.CUSTOMER})
	public ResponseEntity<User> update(@RequestBody User user) {
		authorizationService.guardByUserId(user.getId());
		// We will also check if this resource belongs to the User, even if they pass the @Authorized annotation
		
		return ResponseEntity.accepted().body(userService.update(user));
	}
	
	@Timed(value = "delete.user.time") //custom metric
	@Authorized(allowedRoles = {Role.ADMIN})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		if(userService.delete(id)) {
			return ResponseEntity.accepted().build();
		}
		
		return ResponseEntity.noContent().build();
	}
}
