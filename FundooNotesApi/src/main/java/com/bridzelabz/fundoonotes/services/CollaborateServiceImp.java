package com.bridzelabz.fundoonotes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.NotesRepository;
import com.bridzelabz.fundoonotes.repository.UsersRepository;
import com.bridzelabz.fundoonotes.utility.JWTGenerator;

@Service
public class CollaborateServiceImp implements ICollaboratorServices{
	@Autowired
	private JWTGenerator generateToken;
	
	private UsersEntity user=new UsersEntity();
	
	@Autowired
	 private UsersRepository repository;
	
	@Autowired
	private NotesRepository notesRepository;
	

	@Override
	public NotesEntity addCollaborator(Long notesId, String token) {
		
		Long userId=generateToken.parseJWTToken(token);
		user=repository.getusersByid(userId);
		if(user!=null) {
			NotesEntity note=notesRepository.findBynotesId(notesId);
		}
		return null;
	}

}
