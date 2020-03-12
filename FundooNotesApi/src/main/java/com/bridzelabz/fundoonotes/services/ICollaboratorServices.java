package com.bridzelabz.fundoonotes.services;

import java.util.List;
import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface ICollaboratorServices {
	NotesEntity addCollaborator(Long notesId,String email,String token);
	
	NotesEntity removeCollaborator(Long notesId,String email,String token);
	
	List<NotesEntity> getAllNotesCollaborators(String token);
}
