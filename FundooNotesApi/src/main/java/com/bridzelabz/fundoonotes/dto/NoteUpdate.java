package com.bridzelabz.fundoonotes.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoteUpdate {
	private long noteId;
	
	private String title;

	private String description;

	private String color;

	private boolean isArchieved;

	private boolean isPinned;

	private boolean isTrashed;

	private LocalDateTime updateDate;

}
