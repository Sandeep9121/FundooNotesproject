package com.bridzelabz.fundoonotes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.UsersServices;

@RestController
public class UserController {

	@Autowired
	private UsersServices usersService;

	@PostMapping("/users/register")
	// @RequestMapping(method = RequestMethod.POST,value = "users/register")
	public ResponseEntity<Response> userRegistration(@RequestBody UsersDto userdto) {
		if (usersService.addUsers(userdto)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("U have been Registered Successfully", 200, userdto));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("U are already Registered", 400, userdto));
		}
	}

	/*
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	 * Response(bindingResult.getAllErrors().get(0).getDefaultMessage(),400,null));
	 */
	@GetMapping(value = "/users")
	public List<UsersEntity> getUsers() {
		return usersService.getUserDetails();
	}

	@GetMapping("/users/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) {

		if (usersService.verify(token)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("U have verified Successfully", 400));
		}

		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 400));

		}
	}

	@GetMapping(value = "/users/{userId}")
	public Optional<UsersEntity> getUserById(@PathVariable long userId) {
		return usersService.getuserById(userId);
	}
}
