package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.ICollaboratorServices;

@RestController
public class CollaboratorController {
@Autowired
private ICollaboratorServices serviceCollaborator;


@PostMapping("/collaborator/addCollaborator")
public ResponseEntity<Response> addsCollaborator(@RequestParam("notesId") Long notesId,
		@RequestParam("email") String email, @RequestHeader("token") String token){
	NotesEntity note=serviceCollaborator.addCollaborator(notesId, email, token);
	 if(note!=null) {
		 return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Collaborator is Added"));
	 }
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response("Collaborator is not added"));
	
}
}
