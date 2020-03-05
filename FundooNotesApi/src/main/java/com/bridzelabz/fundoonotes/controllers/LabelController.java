package com.bridzelabz.fundoonotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridzelabz.fundoonotes.services.ILabelServices;

@RestController
public class LabelController {
 @Autowired
private ILabelServices labelServices;
 
 @PostMapping("/label/create")
 public ResponseEntity<Response> 
}
