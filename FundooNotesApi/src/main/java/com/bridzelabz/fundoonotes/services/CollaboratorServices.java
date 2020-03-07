package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface CollaboratorServices {
	NotesEntity addCollaborator(Long notesId,String token);
	

}
