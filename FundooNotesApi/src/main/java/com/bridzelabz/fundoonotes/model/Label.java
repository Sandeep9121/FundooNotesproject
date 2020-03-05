package com.bridzelabz.fundoonotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	

}
