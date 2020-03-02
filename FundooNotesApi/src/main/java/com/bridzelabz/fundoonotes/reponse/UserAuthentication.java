package com.bridzelabz.fundoonotes.reponse;

import lombok.Data;

@Data
public class UserAuthentication {
	private String token;  
	private  int statusCode;
	private  Object obj;
	public UserAuthentication(String token, int statusCode, Object obj) {
		super();
		this.token = token;
		this.statusCode = statusCode;
		this.obj = obj;
	}

}
