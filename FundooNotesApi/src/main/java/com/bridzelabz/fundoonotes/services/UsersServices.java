package com.bridzelabz.fundoonotes.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bridzelabz.fundoonotes.dto.UpdatePassword;
import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;

public interface UsersServices {
	
	public boolean addUsers(UsersDto userdto);
	
	public boolean isUserAlreadyRegistered(String email);
	
	public boolean getUserById(long userId);
	
	public UsersEntity updatePassword(String token,UpdatePassword password);

	public Optional<UsersEntity> getuserById(long userId);
	
	public boolean verify(String token);
	
	public UsersEntity login(UsersDto usersdto);

	public List<UsersEntity> getUserDetails();
	
	
	
	
	
}
