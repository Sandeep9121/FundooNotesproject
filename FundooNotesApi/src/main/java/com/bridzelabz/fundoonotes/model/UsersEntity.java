package com.bridzelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name = "Userdata")
public class UsersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;

	private String name;

	private String email;

	private String password;
	
	private Long mobileNumber;

	private LocalDateTime date;

	private boolean isVerified;

}
