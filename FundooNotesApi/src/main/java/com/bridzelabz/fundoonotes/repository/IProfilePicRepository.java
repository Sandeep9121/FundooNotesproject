package com.bridzelabz.fundoonotes.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProfilePicRepository extends JpaRepository<Profile,Long> {
     @Query(value ="select *from profile where user_id=?")
	Profile findByUserid(Long userId);
	
}
