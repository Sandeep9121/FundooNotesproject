package com.bridzelabz.fundoonotes.utility;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JWTGenerator {
	public static final String SECRETE="9121837953";
	
	
	public String generateWebToken(long  l) {
		
	
			//System.out.println(JWT.create().withClaim("email",email).sign(Algorithm.HMAC512(SECRETE)));
			return JWT.create().withClaim("email",l).sign(Algorithm.HMAC512(SECRETE));
					
			
	
	}
	/*
	 * method to parse token to long
	 */

	public long parseJWTToken(String jwtToken) {
		long userId =0;
		if(jwtToken !=null) {
			 userId = JWT.require(Algorithm.HMAC512(SECRETE)).build().verify(jwtToken).getClaim("id").asLong();	
		}
		return userId;
	}

}
