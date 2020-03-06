package com.bridzelabz.fundoonotes.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;
import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
@Entity
public class NotesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;
	
	private String title;
	
	private String description;
	
	private String color;

	private boolean isArchieved;

	private boolean isPinned;

	private boolean isTrashed;

	private LocalDateTime notesCreatedDate;
	
	private LocalDateTime updateDate; 
	
	private LocalDateTime reminder;

	@JoinColumn(name="user_id")
	@ManyToMany
	@JoinTable(name="notes_id", joinColumns = {@JoinColumn(name ="notes_id")},inverseJoinColumns = {
			@JoinColumn(name="label_id")})
	private List<Label> list;
	
}
