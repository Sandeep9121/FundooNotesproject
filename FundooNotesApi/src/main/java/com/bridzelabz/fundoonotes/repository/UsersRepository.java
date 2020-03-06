package com.bridzelabz.fundoonotes.repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridzelabz.fundoonotes.model.UsersEntity;


@Component
public class UsersRepository {
	@Autowired
	private EntityManager entityManager;
@Transactional
	public UsersEntity getusersByid(Long userId) {
		Session session = entityManager.unwrap(Session.class);
		Query<?> q = session.createQuery("form UsersEntity where user_id=:userId");
		q.setParameter("userId",userId);
		return (UsersEntity) q.uniqueResult();
		
	}
}
