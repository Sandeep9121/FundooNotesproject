package com.bridzelabz.fundoonotes.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UsersDto {
	
private String name;
private String email;
private String password;
private Long mobileNumber;
 
}