/*********************************************************************
 * 
 * @author Mr Sandeep
 * 
 *@since March8
 * 
 * Notes Controller having api Crud apis trash ,archive, pinned get all notes ..
 * 
 **********************************/



package com.bridzelabz.fundoonotes.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridzelabz.fundoonotes.dto.NoteDto;
import com.bridzelabz.fundoonotes.dto.NoteUpdate;
import com.bridzelabz.fundoonotes.dto.ReminderDto;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.ICollaboratorServices;
import com.bridzelabz.fundoonotes.services.INoteServices;

@RestController

public class NoteController {
	@Autowired
	private INoteServices noteServices;
	@Autowired
	private ICollaboratorServices 	serviceCollaborator;

	@PostMapping("/note/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestHeader("token") String token) {
		if(noteServices.createNote(noteDto, token)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note created"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Unable to create Notes"));
		
	}

	@PostMapping("/note/update")
	public ResponseEntity<Response> updatenote(@RequestBody NoteUpdate updateNote, @RequestHeader String token) {
		if(noteServices.updateNote(updateNote, token)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Updated"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("UNABLE TO UPDATE"));
	}

	@DeleteMapping("/note/delete/{notesId}")
	public ResponseEntity<Response> delete(@PathVariable long notesId, @RequestHeader("token") String token) {
		noteServices.deleteNote(notesId, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is Deleted"));
	}

	@PostMapping("/note/archieve/{notesId}")
	public ResponseEntity<Response> archieve(@PathVariable long notesId, @RequestHeader("token") String token) {
		if(noteServices.archieveNote(notesId, token)){
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is archieved"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to archieved"));
	}

	@PostMapping("/note/pinNote/{notesId}")
	public ResponseEntity<Response> pinNote(@PathVariable long notesId, @RequestHeader("token") String token) {
		if(noteServices.pinNote(notesId, token)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is pinned"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to pin"));
	}
	
	
	@PostMapping("/note/addColor/{notesId}")
	public ResponseEntity<Response> addColor(@PathVariable("notesId") Long notesId, @RequestParam("color") String color, @RequestHeader("token") String token)
	{
		if(noteServices.addColor(notesId, token, color)){
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is colored"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to colored"));
	}
	
	@PostMapping("/note/trashed/{notesId}")
	public ResponseEntity<Response> trashed(@PathVariable Long notesId, @RequestHeader String token){
		if(noteServices.trashed(token, notesId)) {	
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is trashed"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to trashed"));
		
		
	}
	
	@PostMapping("/note/restore/{notesId}")
	public ResponseEntity<Response> restore(@PathVariable Long notesId, @RequestHeader String token){
		if(noteServices.restored(token, notesId)) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is restored"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("note is not deleted"));
		
	}
	
	@PostMapping("/note/addReminder/")
	public ResponseEntity<Response> reminder(@RequestParam("notesId") Long notesId, @RequestHeader String token ,@RequestBody ReminderDto reminder){
      if(noteServices.addReminder(token, notesId, reminder)){
    	return  ResponseEntity.status(HttpStatus.CREATED).body(new Response("Reminder is Added"));
      }
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to add Reminder"));
	}
	

	@PostMapping("/note/removeReminder/")
	public ResponseEntity<Response> removeReminder(@RequestParam("notesId") Long notesId, @RequestHeader String token){
      if(noteServices.removeReminder(token, notesId)){
    	return  ResponseEntity.status(HttpStatus.CREATED).body(new Response("Reminder is removed"));
      }
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("unable to remove remainder"));
	}
	
	@GetMapping("/note/allNotes")
	public ResponseEntity<Response> getAllnotes(@RequestHeader("token") String token){
		List<NotesEntity> allnotes=noteServices.getAllnotes(token);
		if(allnotes!=null) {
	    return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("list of your notes", allnotes));
		}
		return  ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("List is Empty"));
	}
	
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
	

	@DeleteMapping("/collaborator/removeCollaborator")
	public ResponseEntity<Response> removeCollaborator(@RequestParam("notesId") Long notesId,
			@RequestParam("email") String email, @RequestHeader("token") String token){
		NotesEntity note=serviceCollaborator.removeCollaborator(notesId, email, token);
		 if(note!=null) {
			 return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Collaborator is Added"));
		 }
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("Collaborator is not added"));
	
	}
	
	
	
	
	
	
}
