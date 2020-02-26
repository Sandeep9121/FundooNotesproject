package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.dto.UsersDto;
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
	
}
