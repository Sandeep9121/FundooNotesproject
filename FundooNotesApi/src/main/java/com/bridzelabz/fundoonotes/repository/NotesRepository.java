package com.bridzelabz.fundoonotes.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;

@Repository
public class NotesRepository implements INoteRepository {
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
		Query<?> q = session.createQuery("from NotesEntity where notesId=:notesId");
		q.setParameter("notesId", notesId);
		return (NotesEntity) q.uniqueResult();
	}

	@Override
	@Transactional
	public int deleteNote(long notesId, NotesEntity note) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> q = session.createQuery("delete from NotesEntity where notesId=:notesId");
		q.setParameter("notesId", notesId);
		return q.executeUpdate();
	}

	@Transactional
	public boolean setTrashed(String token, long notesId) {
		Session session = entityManager.unwrap(Session.class);
		NotesEntity notes = findBynotesId(notesId);
		if (!notes.isTrashed()) {
			notes.setTrashed(true);
			// note.setUpdateDate(LocalDateTime.now());
			notes.setNotesCreatedDate(LocalDateTime.now());
			session.save(notes);
		}
		return true;
	}

	@Transactional
	public boolean setRestored(Long userId, long notesId) {
		Session session = entityManager.unwrap(Session.class);
		NotesEntity notes = findBynotesId(notesId);
		if (notes.getNotesId().equals(userId)) {
			if (notes.isTrashed())
				notes.setTrashed(false);
			notes.setNotesCreatedDate(LocalDateTime.now());
			session.saveOrUpdate(notes);
			return true;
		}

		return false;
	}
	
	@Transactional
	public List<NotesEntity> getAllNotes(long userId) {
		Session session = entityManager.unwrap(Session.class);
		/*Query<?> q = session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC");
		return (List<NotesEntity>) q.getResultList();*/
		return session.createQuery(" from NotesEntity where notesId='"+userId+"'"+"and  is_trashed=false and  is_archieved=false ORDER BY notes_id DESC").getResultList();
	}





}
