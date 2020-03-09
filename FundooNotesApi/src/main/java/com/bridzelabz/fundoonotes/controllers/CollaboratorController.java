package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.ICollaboratorServices;

@RestController
@RequestMapping("/collaborator")
public class CollaboratorController {
@Autowired
private ICollaboratorServices serviceCollaborator;


@PostMapping("/addCollaborator")
public ResponseEntity<Response> addCollaborator(@RequestParam("notesId") Long notesid,
		@RequestParam("email") String email, @RequestHeader("token") String token){
		return null;
	
}
}
