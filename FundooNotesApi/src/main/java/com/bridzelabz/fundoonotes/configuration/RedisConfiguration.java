package com.bridzelabz.fundoonotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
	   @Bean
	    JedisConnectionFactory jedisConnectionFactory() {
			return  new JedisConnectionFactory(); 
	   }
	   @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	        RedisTemplate<String,Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(jedisConnectionFactory());
	        template.setEnableTransactionSupport(true);
	        return template;
	    }
	   
}
/*  RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
	        redisStandaloneConfiguration.setPassword(RedisPassword.of("password"));
	        return new JedisConnectionFactory(redisStandaloneConfiguration);
	    }

	   
}*/
	 /*   @Bean
	    public RedisTemplate redisTemplate() {
	        RedisTemplate template = new RedisTemplate<>();
	        template.setConnectionFactory(jedisConnectionFactory());
	        return template;
	    }
	}*/