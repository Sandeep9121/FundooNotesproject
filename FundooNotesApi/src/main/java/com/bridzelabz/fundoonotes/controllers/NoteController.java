package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.NoteServices;

@RestController

public class NoteController {
	@Autowired
	private NoteServices noteServices;
	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestHeader("token") String token) {
	noteServices.createNote(noteDto,token);
	return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200,noteDto));
	}

	@PostMapping("/note/update")
	public ResponseEntity<Response> updatenote(@RequestBody NoteUpdate updateNote,@RequestHeader String token){
		noteServices.updateNote(updateNote, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Updated", 200,updateNote));

	}
	
	@DeleteMapping("/note/delete/{notesId}")
	public ResponseEntity<Response> delete(@PathVariable long notesId,@RequestHeader("token") String token ){
		noteServices.deleteNote(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Deleted", 200,notesId));

		
		
	}
}
