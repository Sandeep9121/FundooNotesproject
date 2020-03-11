package com.bridzelabz.fundoonotes.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
  @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
	  RestHighLevelClient rest=new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));
		return rest;
	  
  }
}
