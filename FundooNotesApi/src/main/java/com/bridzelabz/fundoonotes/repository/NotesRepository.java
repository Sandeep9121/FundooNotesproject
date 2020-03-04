package com.bridzelabz.fundoonotes.repository;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bridzelabz.fundoonotes.model.NotesEntity;
@Repository
public class NotesRepository implements NoteRepository{
	@Autowired
	private EntityManager entityManager;

	public NotesEntity createNote(NotesEntity note) {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(note);
			return note;
	}



}
