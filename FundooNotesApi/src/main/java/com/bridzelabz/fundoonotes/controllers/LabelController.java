/*********************************************************************
 * 
 * @author Mr Sandeep
 * 
 *@since March11
 * 
 * Notes Controller having api Crud api, in label and having List of notes individual ..
 * 
 **********************************/


package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.dto.LabelUpdate;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.ILabelServices;

@RestController
public class LabelController {
 @Autowired
private ILabelServices labelServices;
 
 @PostMapping("/label/create")
 public ResponseEntity<Response> createLabel(@RequestBody LabelDto labelDto,@RequestHeader("token") String token){
	 if(labelServices.createLabel(labelDto, token)) {
	 return  ResponseEntity.status(HttpStatus.CREATED).body(new Response("label is Created")); 
 }
	return ResponseEntity.status(HttpStatus.CREATED).body(new Response("label is not Created"));
	 }
 
 
 @PutMapping("/label/updateLabel")
  public ResponseEntity<Response> updateLabel(@RequestBody LabelUpdate lableUpdate,@RequestHeader("token") String token){
	  if(labelServices.updateLabel(lableUpdate, token)) {
	 return ResponseEntity.status(HttpStatus.OK).body(new Response("label is Updated"));
	  }
	return ResponseEntity.status(HttpStatus.OK).body(new Response("label is not  Updated"));
 }
 
 @DeleteMapping("/label/delete")
 public ResponseEntity<Response> deleteLabel(@RequestParam("labelId") Long labelId,@RequestHeader("token") String token,@RequestParam ("notesId") Long notesId){
	labelServices.removeLabel(labelId, notesId, token);
	 return ResponseEntity.status(HttpStatus.OK).body(new Response("label is deleted"));
	 
 }
 
 @PostMapping("/addLabel")
 public ResponseEntity<Response> addlabel(@RequestParam("labelId") Long labelId,@RequestHeader("token") String token,@RequestParam ("notesId") Long notesId){
	  labelServices.addLabel(labelId, notesId, token);
	 return ResponseEntity.status(HttpStatus.OK).body(new Response("label is added"));
	 
 }
 
}
