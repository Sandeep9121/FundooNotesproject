package com.bridzelabz.fundoonotes.repository;


import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface INoteRepository {


	public NotesEntity createNote(NotesEntity note);
	public NotesEntity  findBynotesId(long notesid);
	public  int deleteNote(long notesId, NotesEntity note);
	public boolean setTrashed(String token, long notesId);
	public boolean setRestored(Long userId, long notesId);
}
