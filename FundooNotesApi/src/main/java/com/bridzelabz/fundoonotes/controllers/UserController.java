package com.bridzelabz.fundoonotes.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.services.UsersServices;
@RestController
public class UserController {
	
   @Autowired
   private UsersServices usersService;
   @PostMapping("/users/register")
 //  @RequestMapping(method = RequestMethod.POST,value = "users/register")
  public void userRegistration(@RequestBody UsersDto userdto ) {
	  usersService.addUsers(userdto);
  }
   @GetMapping(value="/users")
   public List<UsersEntity> getUsers(){
	   
	   return usersService.getUserDetails();
   }
   @GetMapping(value="/users/{userId}")
   public Optional<UsersEntity> getUserById(@PathVariable long userId){
	return usersService.getuserById(userId);	   
   }
}
