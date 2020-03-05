package com.bridzelabz.fundoonotes.services;

import java.util.List;

import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
import com.bridzelabz.fundoonotes.dto.ReminderDto;
import com.bridzelabz.fundoonotes.model.NotesEntity;

public interface INoteServices {

	boolean createNote(NoteDto noteDto, String token);

	boolean updateNote(NoteUpdate updateNote, String token);

	int deleteNote(long notesId, String token);

	boolean archieveNote(long notesId, String token);

	boolean pinNote(long notesId, String token);

	boolean addColor(long noteId, String token, String color);

	boolean trashed(String token, long notesId);

	boolean restored(String token, long notesId);

	boolean addReminder(String token, Long notesId, ReminderDto reminder);

	boolean removeReminder(String token, Long notesId);
	
	List <NotesEntity> getAllnotes(String token);
	
}
