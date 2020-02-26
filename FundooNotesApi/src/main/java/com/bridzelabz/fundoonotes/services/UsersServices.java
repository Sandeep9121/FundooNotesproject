package com.bridzelabz.fundoonotes.services;

import java.util.List;
import java.util.Map;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;

public interface UsersServices {
	public void addUsers(UsersDto userdto);
	
	
	
	public List<UsersEntity> getUserDetails();

	public Map<String, Object> getUserIdById(long userId);
    
	public boolean getUserById(long userId);
	
	
	
	
}
