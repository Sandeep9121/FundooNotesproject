package com.bridzelabz.fundoonotes.repository;

import java.util.List;

import com.bridzelabz.fundoonotes.model.NotesEntity;


public interface INoteRepository {

	NotesEntity createNote(NotesEntity note);

	NotesEntity findBynotesId(long notesid);

	int deleteNote(long notesId, NotesEntity note);

	boolean setTrashed(String token, long notesId);

	boolean setRestored(Long userId, long notesId);
    
	 List<NotesEntity> getAllNotes(long userId);

}
