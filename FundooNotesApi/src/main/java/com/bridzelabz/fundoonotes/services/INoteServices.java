package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
import com.bridzelabz.fundoonotes.dto.ReminderDto;


public interface INoteServices {
	
	public boolean createNote(NoteDto noteDto, String token);

	public boolean updateNote(NoteUpdate updateNote, String token);
	
	public int deleteNote(long notesId , String token);
    
	boolean archieveNote(long notesId,String token);
    
	public boolean pinNote(long notesId, String token);
	
	public boolean addColor(long noteId, String token,String color);
	
     boolean trashed(String token,long notesId);
	
	 boolean restored(String token,long notesId);
	
	 boolean addReminder(String token,Long notesId, ReminderDto reminder);
	 
	 boolean removeReminder(String token,Long notesId, ReminderDto reminder);
}
