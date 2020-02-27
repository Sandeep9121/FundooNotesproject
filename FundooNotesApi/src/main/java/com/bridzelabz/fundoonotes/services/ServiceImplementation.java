package com.bridzelabz.fundoonotes.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.reponse.EmailData;
import com.bridzelabz.fundoonotes.reponse.EmailResponse;
import com.bridzelabz.fundoonotes.reponse.Response;
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
	private EmailProviderService mailProvider;	
    @Autowired
    private EmailData emailData;
    @Autowired
    private Response response; 
    @Autowired
    private EmailResponse emailResponse;
    
    
	@Override
	@Transactional
	public void addUsers(UsersDto usersdto) {
		BeanUtils.copyProperties(usersdto, user);
		user.setPassword(encryptPass.encode(usersdto.getPassword()));
		user.setDate(LocalDateTime.now());
		//System.out.println(generateToken.generateWebToken(usersdto.getEmail()));
		userRepository.save(user);
		
		
		/*String response=emailResponse.mailform("http://localhost:8080/users/verify/",
				generateToken.generateWebToken( usersdto.getEmail() ));*/
		String response=emailResponse.mailform("http://localhost:8080/users/verify/",
				generateToken.generateWebToken( user.getUserId() ));

	}

	/*
	 * to get all details in
	 */
	@Override
	public List<UsersEntity> getUserDetails() {
		List<UsersEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	@Override
	public boolean getUserById(long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<UsersEntity> getuserById(long userId) {
		return userRepository.findById(userId);

	}

}
