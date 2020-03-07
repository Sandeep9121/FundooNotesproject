package com.bridzelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridzelabz.fundoonotes.model.LabelEntity;

public interface ILabelRepository extends JpaRepository<LabelEntity,Long>{
 
	@Query("from Label where user_id=:userId and name=:name")
   LabelEntity fetchLabel(long userId, String name);
}
