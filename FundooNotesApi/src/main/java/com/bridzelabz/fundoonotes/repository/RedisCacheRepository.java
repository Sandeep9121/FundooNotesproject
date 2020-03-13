package com.bridzelabz.fundoonotes.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridzelabz.fundoonotes.constants.Constants;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.bridzelabz.fundoonotes.model.UsersEntity;

import lombok.Getter;
@Repository
@Getter
public class RedisCacheRepository {
   private String key=Constants.KEY;
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, Long, Object> hashOperation;

	public RedisCacheRepository(RedisTemplate<String, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperation = redisTemplate.opsForHash();
	}
     
	public void save(NotesEntity notes) {
		 hashOperation.put("notes", notes.getNotesId(),notes );
	}
	public void save(UsersEntity user) {
		hashOperation.put("user",user.getUserId(), user);
	}
}
