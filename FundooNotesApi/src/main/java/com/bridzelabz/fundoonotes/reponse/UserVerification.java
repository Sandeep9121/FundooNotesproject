package com.bridzelabz.fundoonotes.reponse;

import lombok.Data;

@Data
public class UserVerification {
	private String token;  
	private  int statusCode;
	private  Object obj;
	public UserVerification(String token, int sta, Object obj) {
		super();
		this.token = token;
		this.obj = obj;
	}
	public UserVerification(String token) {
		super();
		this.token = token;
	}

}
