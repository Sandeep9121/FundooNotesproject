package com.bridzelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="Userdata")
public class UsersEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "user_id")
	private long userId;
	
	/*
	 * @Column(name = "user_name",nullable = false )
	 * 
	 * @NotEmpty(message = "Please provide an username")
	 */
	private String name;
	
	/*
	 * @Column(name = "password")
	 * 
	 * @Size(min = 5)
	 * 
	 * @NotEmpty(message = "Please provide password")
	 */
	private String password;
	
	/*
	 * @Column(name = "email", nullable = false, unique = true)
	 * 
	 * @NotEmpty(message = "Please provide an e-mail")
	 */
	private String email;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	/*
	 * @Column(name = "mobileNumber" ,nullable = false )
	 * 
	 * @Size(min =10 ,max = 12)
	 * 
	 * @NotEmpty(message = "Please provide an mobileNumber")
	 */
	private long mobileNumber;
	
	/*
	 * @Column(name = "Date")
	 */	private LocalDateTime date;
	
	/*
	 * @Column(name = "verify", columnDefinition = "boolean default false", nullable
	 * = false)
	 */	private boolean isVerified;

}
