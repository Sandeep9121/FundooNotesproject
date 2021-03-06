/*********************************************************************
 * 
 * @author Mr Sandeep
 * 
 *@since March1
 * 
 * Notes Controller having api Crud apis trash ,archive,user list..
 * 
 **********************************/


package com.bridzelabz.fundoonotes.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridzelabz.fundoonotes.dto.LoginDto;
import com.bridzelabz.fundoonotes.dto.UpdatePassword;
import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.ProfileEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.reponse.UserVerification;
import com.bridzelabz.fundoonotes.services.IUsersServices;
import com.bridzelabz.fundoonotes.services.ProfileServiceImp;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;

import io.swagger.annotations.ApiOperation;

@RestController

public class UserController {
	@Autowired
	private UsersEntity user;
    
	
	@Autowired
	private JWTGenerator generateToken;

	@Autowired
	private IUsersServices usersService;
	
	@Autowired
	private ProfileServiceImp profileService;

	@PostMapping("/users/register")
	// @RequestMapping(method = RequestMethod.POST,value = "users/register")
	public ResponseEntity<Response> userRegistration(@RequestBody UsersDto userdto) {
		try {
			if (usersService.addUsers(userdto)) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new Response("U have been Registered Successfully"));
			} else {
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
						.body(new Response("U are already Registered"));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/users")
	public List<UsersEntity> getUsers() {
		return usersService.getUserDetails();
	}

	@GetMapping("/users/verify/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token) {

		if (usersService.verify(token)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("U have verified Successfully", 200));
		}

		else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("not verified", 400));

		}
	}

	@GetMapping(value = "/users/{token}")
	public ResponseEntity<Response> getUserById(@PathVariable("token") String token) {
		UsersEntity user=usersService.getuserById(token);
		if(user!=null) {
		return ResponseEntity.status(HttpStatus.OK).body(new Response("User is fetched",user));
	}
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Unable to  fetch",user));
		}

	@PostMapping("/users/login")
	public ResponseEntity<UserVerification> login(@RequestBody LoginDto loginData) {
		UsersEntity userLogin = usersService.login(loginData);
		String token = generateToken.generateWebToken(userLogin.getUserId());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserVerification(token,"u have been Successfully Login",userLogin));
	}

	@PutMapping("/users/updatePassword/{token}")
	public ResponseEntity<Response> updatePassword(@Valid @PathVariable("token") String token,
			@RequestBody UpdatePassword password, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("failed "));
		}
		user = usersService.updatePassword(token, password);
		if (user != null)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("password is updated Succesfully"));
		else
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new Response("password and confirm password is not matched"));

	}

	@PostMapping("/users/uploadProfilePicture")
	@ApiOperation(value = "Api to upload profile pic of User for Fundoonotes", response = Response.class)
	public ResponseEntity<Response> addProfilePic(@ModelAttribute MultipartFile file,
			@RequestHeader("token") String token) {
		ProfileEntity profile = profileService.storeobjectInS3(file,file.getOriginalFilename(), file.getContentType(),
				token);
		if(profile.getUserlabel()!=null) {
			return  ResponseEntity.status(HttpStatus.OK)
					.body(new Response("profile added sucessfully"));
		}
		return  ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Response("profile is not added"));

	}
}