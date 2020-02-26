package com.bridzelabz.fundoonotes.services;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.dto.UsersDto;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.UsersRepository;

@Service
public class ServiceImplementation implements UsersServices {
	@Autowired
	private UsersEntity user;
	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encryptPass;

	@Override
	@Transactional
	public void addUsers(UsersDto usersdto) {
		BeanUtils.copyProperties(usersdto, user);
		user.setPassword(encryptPass.encode(usersdto.getPassword()));
		user.setDate(LocalDateTime.now());
		userRepository.save(user);

	}

	@Override
	public List<UsersEntity> getUserDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUserIdById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getUserById(long userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
