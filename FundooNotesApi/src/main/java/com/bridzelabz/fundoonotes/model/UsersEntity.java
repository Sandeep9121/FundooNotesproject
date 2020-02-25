package com.bridzelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "user_name",nullable = false )
	@NotEmpty(message = "Please provide an username")
	private String name;
	
	@Column(name = "password")
	@Size(min = 5)
	@NotEmpty(message = "Please provide password")
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@Column(name = "mobileNumber" ,nullable = false )
	@Size(min =10 ,max = 12)
	@NotEmpty(message = "Please provide an mobileNumber")
	private long mobileNumber;
	
	@Column(name = "Date")
	private LocalDateTime date;
	
	@Column(name = "verify", columnDefinition = "boolean default false", nullable = false)
	private boolean isVerified;

}
