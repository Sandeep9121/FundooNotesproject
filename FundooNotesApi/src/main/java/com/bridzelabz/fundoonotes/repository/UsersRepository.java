package com.bridzelabz.fundoonotes.repository;

import org.springframework.data.repository.CrudRepository;

import com.bridzelabz.fundoonotes.model.UsersEntity;

public interface UsersRepository extends CrudRepository<UsersEntity, Long>{

}
