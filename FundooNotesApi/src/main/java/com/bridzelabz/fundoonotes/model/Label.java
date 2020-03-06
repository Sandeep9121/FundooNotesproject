package com.bridzelabz.fundoonotes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data

@Entity
@Component
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int labelId;
	private String name;
	private long userId;
	
    @JoinColumn(name = "user_id")
    @ManyToMany
    @JoinTable(name ="notes_label",joinColumns = { @JoinColumn(name="label_id")},inverseJoinColumns = {
    		@JoinColumn(name="notes_id")})
	private List<NotesEntity> list;
}
