package com.bridzelabz.fundoonotes.repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridzelabz.fundoonotes.model.NotesEntity;
@Repository
public class NotesRepository implements INoteRepository{
	@Autowired
	private EntityManager entityManager;
  @Transactional
	public NotesEntity createNote(NotesEntity note) {
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(note);
			return note;
	}

  @Transactional
	public NotesEntity findBynotesId(long notesId) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> q=session.createQuery("from NotesEntity where notesId=:notesId");
		q.setParameter("notesId",notesId);
		return (NotesEntity) q.uniqueResult();
	}

@Override
@Transactional
public int deleteNote(long notesId, NotesEntity note) {
	Session session = entityManager.unwrap(Session.class);
	Query<?> q=session.createQuery("delete from NotesEntity where notesId=:notesId");
	q.setParameter("notesId",notesId);
	return q.executeUpdate();
}



}
