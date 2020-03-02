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

import com.bridzelabz.fundoonotes.custom_exceptions.ExitsEmailException;
import com.bridzelabz.fundoonotes.dto.UpdatePassword;
import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.reponse.EmailData;
import com.bridzelabz.fundoonotes.repository.UsersRepository;
import com.bridzelabz.fundoonotes.utility.EmailProviderService;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	@Autowired
	private UpdatePassword psw;

	@Autowired
	private EmailProviderService em;

	@Transactional
	public boolean addUsers(UsersDto usersdto){
		// UsersEntity user = new UsersEntity();
		Optional<UsersEntity> emailExists=userRepository.findUserByEmail(usersdto.getEmail());
		  if(emailExists.isPresent()) {
			 throw new ExitsEmailException("u have been already registered");
		  }

		BeanUtils.copyProperties(usersdto, user);

		user.setPassword(encryptPass.encode(usersdto.getPassword()));

		user.setDate(LocalDateTime.now());

		user.setVerified(false);

		userRepository.save(user);

		String token = generateToken.generateWebToken(user.getUserId());
		/*
		 * token to collect
		 */
		log.info("your token to collect-" + token);

		String url = "http://192.168.1.127:8081/users/verify/";
		String body = url + token;

		emailData.setEmail(usersdto.getEmail());

		emailData.setSubject("click below link to verify your registration");

		emailData.setBody(body);

		em.sendMail(emailData.getEmail(), emailData.getSubject(), emailData.getBody());

		return true;
	}

	@Transactional
	public boolean getUserById(long userId) {
		return false;
	}

	@Transactional
	public Optional<UsersEntity> getuserById(long userId) {
		return userRepository.findById(userId);
	}

	@Override
	@Transactional
	public boolean verify(String token) {

		long userid = generateToken.parseJWTToken(token);

		Optional<UsersEntity> checkUser = userRepository.findById(userid);

		if (checkUser.isPresent()) {
			checkUser.get().setVerified(true);
			userRepository.save(checkUser.get());
			// userRepository.findById(userid).get().setVerified(true);
			// userRepository.save(userRepository.findById(userid).get());

			return true;
		} else {
			return false;
		}
	}

	
	
	@Override
	@Transactional
	public UsersEntity login(UsersDto usersdto) {
		return null;
	}

	@Override
	@Transactional
	public List<UsersEntity> getUserDetails() {
		List<UsersEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return null;
	}

	@Override
	@Transactional
	public boolean isUserAlreadyRegistered(String email) {

		Optional<UsersEntity> isUserAlreadyRegistered = userRepository.findUserByEmail(email);

		if (isUserAlreadyRegistered.isPresent() && isUserAlreadyRegistered.get().isVerified()) {

			String body = "http://192.168.1.127:8081/users/updatePassword/"
					+ generateToken.generateWebToken(isUserAlreadyRegistered.get().getUserId());

			em.sendMail(isUserAlreadyRegistered.get().getEmail(), "Updated", body);

			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public UsersEntity updatePassword(String token, UpdatePassword password) {
		if (psw.getNewPassword().equals(psw.getChangepassword())) {

			long userId = generateToken.parseJWTToken(token);

			Optional<UsersEntity> isUserPresent = userRepository.findById(userId);

			if (isUserPresent.isPresent()) {

				String encryptass = encryptPass.encode(psw.getNewPassword());

				isUserPresent.get().setPassword(encryptass);

				userRepository.save(isUserPresent.get());

				return isUserPresent.get();

			}

		}

		return null;
	}

}
