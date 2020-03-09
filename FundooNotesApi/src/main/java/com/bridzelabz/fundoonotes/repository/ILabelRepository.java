package com.bridzelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridzelabz.fundoonotes.model.Label;

public interface ILabelRepository extends JpaRepository<Label,Long>{
 
	@Query("from Label where user_id=:userId and name=:name")
   Label fetchLabel(long userId, String name);
}
