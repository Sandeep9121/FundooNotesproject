package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.services.UsersServices;
@RestController
public class UserController {
	
   @Autowired
   private UsersServices usersService;
   @RequestMapping(method = RequestMethod.POST,value = "users/register")
  public void userRegistration(@RequestBody UsersEntity user ) {
	  
  }
	
}
