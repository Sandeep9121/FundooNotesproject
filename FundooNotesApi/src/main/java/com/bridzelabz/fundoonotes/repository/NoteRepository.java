package com.bridzelabz.fundoonotes.repository;


import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface NoteRepository {


	public NotesEntity createNote(NotesEntity note);
	public NotesEntity  findBynotesId(long notesid);
	public NotesEntity deleteNote(long notesId, NotesEntity note);
}
