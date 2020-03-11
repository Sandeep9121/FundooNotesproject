package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface ICollaboratorServices {
	NotesEntity addCollaborator(Long notesId,String email,String token);
	
	NotesEntity removeCollaborator(Long notesId,String email,String token);
}
