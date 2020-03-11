package com.bridzelabz.fundoonotes.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface ICollaboratorServices {
	NotesEntity addCollaborator(Long notesId,String email,String token);
	
	NotesEntity removeCollaborator(Long notesId,String email,String token);
	
	List<NotesEntity> getAllNotesCollaborators(String token) throws JWTVerificationException,
	IllegalArgumentException,UnsupportedEncodingException;
}
