package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.NoteServices;

@RestController

public class NoteController {
	@Autowired
	private NoteServices noteServices;
	@PostMapping("/note/create")
	public ResponseEntity<Response> registration(@RequestBody NoteDto information, @RequestHeader String token) {
	noteServices.createNote(information,token);

	return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created", 200, information));
	}

}
