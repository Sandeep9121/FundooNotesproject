package com.bridzelabz.fundoonotes.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;

public interface UsersServices {
	public void addUsers(UsersDto userdto);
	
	
	public Optional<UsersEntity> getuserById(long userId);
	public List<UsersEntity> getUserDetails();

	//public Map<String, Object> getUserIdById(long userId);
    
	public boolean getUserById(long userId);
	
	
	
	
}