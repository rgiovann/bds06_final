package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.AuthService;

// Rest Controller (API)
@RestController
@RequestMapping(value = "/users/profile")
public class UserResource {

	@Autowired
	private AuthService authService;

	@GetMapping
	public ResponseEntity<UserDTO> getProfile() {
		return ResponseEntity.ok().body(authService.authenticated());
	}

}
