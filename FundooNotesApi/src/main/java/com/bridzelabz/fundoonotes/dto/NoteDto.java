package com.bridzelabz.fundoonotes.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class NoteDto {
   private String title;
   
   private String description; 
	
	private String color;

	private boolean isArchieved;

	private boolean isPinned;

	private boolean isTrashed;

	private LocalDateTime notesCreatedDate;
	
	private LocalDateTime updateDate; 
	
	
}
