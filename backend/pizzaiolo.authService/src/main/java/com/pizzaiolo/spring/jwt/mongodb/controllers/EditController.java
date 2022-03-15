package com.pizzaiolo.spring.jwt.mongodb.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaiolo.spring.jwt.mongodb.models.Role;
import com.pizzaiolo.spring.jwt.mongodb.models.User;
import com.pizzaiolo.spring.jwt.mongodb.payload.request.EditUserRequest;
import com.pizzaiolo.spring.jwt.mongodb.payload.request.LoginRequest;
import com.pizzaiolo.spring.jwt.mongodb.payload.request.SignupRequest;
import com.pizzaiolo.spring.jwt.mongodb.payload.response.JwtResponse;
import com.pizzaiolo.spring.jwt.mongodb.payload.response.MessageResponse;
import com.pizzaiolo.spring.jwt.mongodb.repository.RoleRepository;
import com.pizzaiolo.spring.jwt.mongodb.repository.UserRepository;
import com.pizzaiolo.spring.jwt.mongodb.security.jwt.JwtUtils;
import com.pizzaiolo.spring.jwt.mongodb.security.services.UserDetailsImpl;
import com.pizzaiolo.spring.jwt.mongodb.models.Address;
import com.pizzaiolo.spring.jwt.mongodb.models.ERole;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class EditController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PutMapping("/editUser/{id}")
	public ResponseEntity<?> editUser(@PathVariable String id ,@Valid @RequestBody EditUserRequest loginRequest) {
		userRepository.findById(id);
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getLastName(), signUpRequest.getBirthDate(),
				signUpRequest.getEmail(), signUpRequest.getPhone() ,encoder.encode(signUpRequest.getPassword()), true);
		List<Address> address = new ArrayList<Address>();
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "delivery":
					Role deliRole = roleRepository.findByName(ERole.ROLE_DELIVERY)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(deliRole);

					break;
				case "chef":
					Role chefRole = roleRepository.findByName(ERole.ROLE_CHEF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(chefRole);

					break;
				case "manager":
					Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(managerRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		user.setAddress(address);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
}
