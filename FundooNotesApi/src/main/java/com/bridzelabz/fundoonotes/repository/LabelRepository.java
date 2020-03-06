package com.bridzelabz.fundoonotes.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridzelabz.fundoonotes.model.Label;
@Component
public class LabelRepository implements ILabelRepository{
   @Autowired
   private EntityManager entityManager;
	
	
    @Transactional
	public Label saveLabel(Label label) {
    	Session session = entityManager.unwrap(Session.class);
    	session.save(label);
		return label;
	}

}
