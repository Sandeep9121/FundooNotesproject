package com.bridzelabz.fundoonotes.services;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridzelabz.fundoonotes.configuration.ElasticSearchConfig;
import com.bridzelabz.fundoonotes.model.NotesEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ElasticSearchImp implements IElasticSearch{
 private static final String INDEX = "springboot";
private static final String TYPE = "notes_details";
@Autowired
 private ElasticSearchConfig  config;
 @Autowired
 private ObjectMapper objMap;

	public String createNote(NotesEntity notes) {
		Map noteMapper=objMap.convertValue(notes,Map.class);
		IndexRequest indexRequest=new IndexRequest(INDEX,TYPE,String.valueOf(notes.getNotesId()))
				.source(noteMapper);
	    IndexResponse indexResponse = null;
	       try {
			indexResponse=config.client().index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();} 
		return indexResponse.getResult().name();
	}

	public String updateNote(NotesEntity notes) {

		return null;
	}


	public String deleteNotes(NotesEntity notes) {

		return null;
	}

	public List<NotesEntity> searchByTitle(String title) {

		return null;
	}

}
