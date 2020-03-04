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
import com.bridzelabz.fundoonotes.services.INoteServices;

@RestController

public class NoteController {
	@Autowired
	private INoteServices noteServices;

	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestHeader("token") String token) {
		noteServices.createNote(noteDto, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created"));
	}

	@PostMapping("/note/update")
	public ResponseEntity<Response> updatenote(@RequestBody NoteUpdate updateNote, @RequestHeader String token) {
		noteServices.updateNote(updateNote, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Updated"));

	}

	@DeleteMapping("/note/delete/{notesId}")
	public ResponseEntity<Response> delete(@PathVariable long notesId, @RequestHeader("token") String token) {
		noteServices.deleteNote(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Deleted"));
	}

	@PostMapping("/note/archieve/{notesId}")
	public ResponseEntity<Response> archieve(@PathVariable long notesId, @RequestHeader("token") String token) {
		noteServices.archieveNote(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is archieved"));

	}

	@PostMapping("/note/pinNote/{notesId}")
	public ResponseEntity<Response> pinNote(@PathVariable long notesId, @RequestHeader("token") String token) {
		noteServices.pinNote(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is pinned"));

	}

}
