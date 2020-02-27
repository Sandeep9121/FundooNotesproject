package com.bridzelabz.fundoonotes.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTGenerator {
	public static final String SECRETE="9121837953";
	
	
	public String generateWebToken(String  email) {
		
		return JWT.create().withClaim("email",email).sign(Algorithm.HMAC512(SECRETE));
				
	}
	/*
	 * method to parse token to long
	 */

	public String parseJWTToken(String jwtToken) {
		String email =null;
		if(jwtToken !=null) {
			 email = JWT.require(Algorithm.HMAC512(SECRETE)).build().verify(jwtToken).getClaim("id").asString();	
		}
		return email;
	}

}
