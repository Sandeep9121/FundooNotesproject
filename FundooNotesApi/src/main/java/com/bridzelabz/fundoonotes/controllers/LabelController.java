package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.dto.LabelDto;
import com.bridzelabz.fundoonotes.reponse.Response;
import com.bridzelabz.fundoonotes.services.ILabelServices;

@RestController
public class LabelController {
 @Autowired
private ILabelServices labelServices;
 
 @PostMapping("/label/create")
 public ResponseEntity<Response> createLabel(@RequestBody LabelDto labelDto,@RequestHeader("token") String token){
	 labelServices.createLabel(labelDto, token);
	 return  ResponseEntity.status(HttpStatus.CREATED).body(new Response("label is Created")); 
 } 
}
