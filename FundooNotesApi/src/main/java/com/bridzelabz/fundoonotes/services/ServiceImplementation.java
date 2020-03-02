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
	private EmailProviderService em;

	@Override
	@Transactional
	public boolean addUsers(UsersDto usersdto) {
		// UsersEntity user = new UsersEntity();

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

		String url = "http://192.168.1.127:8080/users/verify";
		String body = url + token;

		emailData.setEmail(usersdto.getEmail());

		emailData.setSubject("click below link to verify your registration");

		emailData.setBody(body);

		em.sendMail(emailData.getEmail(), "verfiied", "rrtrr");

		return true;
	}

	public boolean getUserById(long userId) {
		return false;
	}

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
		}

		return false;
	}

	@Override
	public UsersEntity login(UsersDto usersdto) {
		return null;
	}

	@Override
	public List<UsersEntity> getUserDetails() {
		List<UsersEntity> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return null;
	}

}
