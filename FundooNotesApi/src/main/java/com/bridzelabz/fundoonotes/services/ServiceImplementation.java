package com.bridzelabz.fundoonotes.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.reponse.EmailData;

//import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.repository.UsersRepository;
import com.bridzelabz.fundoonotes.utility.EmailProviderService;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;

@Service
public class ServiceImplementation implements UsersServices {
	@Autowired
	private UsersEntity user;
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encryptPass;
	@Autowired
	private JWTGenerator generateToken;	
    @Autowired
    private EmailData emailData;
  /* @Autowired
    private Response response; */
  
    @Autowired
    private EmailProviderService em;
    
	@Override
	@Transactional
	public void addUsers(UsersDto usersdto){
		BeanUtils.copyProperties(usersdto, user);
		user.setPassword(encryptPass.encode(usersdto.getPassword()));
		user.setDate(LocalDateTime.now());
		user.setVerified(false);
		userRepository.save(user);
		//String subject=emailResponse.mailform("http://localhost:8081/users/verify/",generateToken.generateWebToken( user.getUserId() ));
		String token=generateToken.generateWebToken( user.getUserId() );
		String url="http://localhost:8081/users/verify/";
		String subject=url+token;
		emailData.setEmail(usersdto.getEmail());
		emailData.setSubject(subject);
		emailData.setBody("click here to verify");
		em.sendMail(emailData.getEmail(),emailData.getSubject(),emailData.getBody());
		}
	@Override
	public List<UsersEntity> getUserDetails() {	
		return null;
	}

	@Override
	public boolean getUserById(long userId) {
		return false;
	}

	@Override
	public Optional<UsersEntity> getuserById(long userId) {
		return userRepository.findById(userId);

	}

}
