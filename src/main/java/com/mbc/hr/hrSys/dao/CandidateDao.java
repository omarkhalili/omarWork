package com.mbc.hr.hrSys.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mbc.hr.hrSys.dao.documents.Candidate;

@Component
public class CandidateDao {

	public Candidate getById(MongoTemplate mongoTemplate, String candidateId) {
		return mongoTemplate.findById(candidateId, Candidate.class);
	}

	public List<Candidate> getAll(MongoTemplate mongoTemplate) {
		Sort sortByRegDate = Sort.by("regestrationDate").descending();
		Query query = new Query();
		query.with(sortByRegDate);
		return mongoTemplate.find(query, Candidate.class);
	}

	public Candidate insert(MongoTemplate mongoTemplate, Candidate candidate) {
		return mongoTemplate.insert(candidate);
	}
	
	public Candidate save(MongoTemplate mongoTemplate, Candidate candidate) {
		return mongoTemplate.save(candidate);
	}
	
}
