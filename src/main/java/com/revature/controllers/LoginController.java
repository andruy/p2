package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.annotation.Counted;

import com.revature.models.LoginTemplate;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	
	@Timed(value = "login.time") //custom metric
	@Counted(value = "login.fail.counter", recordFailuresOnly = true) //custom metric
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginTemplate loginTemplate) {
		
		return ResponseEntity.ok(userService.login(loginTemplate.getUsername(), loginTemplate.getPassword()));
	}
	
	@Timed(value = "logout.time") //custom metric
	@PostMapping("/logout")
	public ResponseEntity<Void> logout() {
		userService.logout();
		
		return ResponseEntity.accepted().build();
	}
}
