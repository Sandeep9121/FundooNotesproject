package com.bridzelabz.fundoonotes.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
@Entity
public class NotesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long notesId;
	
	private String title;
	
	private String description;
	
	private String color;

	private boolean isArchieved;

	private boolean isPinned;

	private boolean isTrashed;

	private LocalDateTime notesCreatedDate;
	
	private LocalDateTime reminder;

}
