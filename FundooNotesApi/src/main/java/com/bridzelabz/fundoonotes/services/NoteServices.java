package com.bridzelabz.fundoonotes.services;

import com.bridzelabz.fundoonotes.dto.NoteDto;


public interface NoteServices {
	public boolean createNote(NoteDto noteDto, String token);

	


}
