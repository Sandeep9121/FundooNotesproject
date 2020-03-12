package com.bridzelabz.fundoonotes.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.customexception.CollaboratorNotFoundExcepton;
import com.bridzelabz.fundoonotes.customexception.UserNotFoundException;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;
import com.bridzelabz.fundoonotes.repository.IUsersRepository;
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
	 private IUsersRepository usersRepository;
	
	@Autowired
	private NotesRepository notesRepository;
	

@Transactional
	public NotesEntity addCollaborator(Long notesId,String email, String token) {
		UsersEntity user;
	
		UsersEntity	collaborator=repository.getusersByemail(email);
		try {
			Long userId=generateToken.parseJWTToken(token);
			user=repository.getusersByid(userId);
		}catch (Exception e) {
		    throw new UserNotFoundException("USER NOT FOUND WITH GIVEN Id",HttpStatus.NOT_FOUND);
		}
		if(user!=null) {
			if(collaborator!=null) {
			NotesEntity note=notesRepository.findBynotesId(notesId);
		    collaborator.getCollaborateNotes().add(note);
			usersRepository.save(collaborator);
			notesRepository.createNote(note);
			return note;
			}else {
				throw new CollaboratorNotFoundExcepton("Collaborator is null",HttpStatus.NOT_FOUND);
			}
		}else {
			throw new UserNotFoundException("user not found",HttpStatus.NOT_FOUND);
         		}
	}

@Transactional
	public NotesEntity removeCollaborator(Long notesId, String email, String token) {
		UsersEntity user;
		
		UsersEntity	collaborator=repository.getusersByemail(email);
		try {
			Long userId=generateToken.parseJWTToken(token);
			user=repository.getusersByid(userId);
		}catch (Exception e) {
		    throw new UserNotFoundException("USER NOT FOUND WITH GIVEN Id",HttpStatus.NOT_FOUND);
		}
		if(user!=null) {
			if(collaborator!=null) {
				NotesEntity note=notesRepository.findBynotesId(notesId);
				note.getCollaborateUser().remove(collaborator);
				notesRepository.createNote(note);
			}else {
				throw new CollaboratorNotFoundExcepton("there is no collaborator",HttpStatus.NOT_FOUND);
			}
			
		}
		return null;
	}

@Transactional
public List<NotesEntity> getAllNotesCollaborators(String token){
try {
	Long userId=generateToken.parseJWTToken(token);
	user=repository.getusersByid(userId);
}catch (Exception e) {
    throw new UserNotFoundException("USER NOT FOUND WITH GIVEN Id",HttpStatus.NOT_FOUND);
}
	List<NotesEntity> notes=user.getCollaborateNotes();
	return notes;
}

}
