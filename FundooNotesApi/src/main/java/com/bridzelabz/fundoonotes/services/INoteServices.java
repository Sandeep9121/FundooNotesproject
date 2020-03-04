package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;


public interface INoteServices {
	public boolean createNote(NoteDto noteDto, String token);

	public boolean updateNote(NoteUpdate updateNote, String token);
	
	public int deleteNote(long notesId , String token);
    
	boolean archieveNote(long notesId,String token);
    
	public boolean pinNote(long notesId, String token);
}
