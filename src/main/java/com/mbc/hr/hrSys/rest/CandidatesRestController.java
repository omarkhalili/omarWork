package com.mbc.hr.hrSys.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbc.hr.hrSys.dao.documents.Candidate;

@RestController
@RequestMapping("/api")
public class CandidatesRestController {

	private MongoTemplate mongoTemplate;

	public CandidatesRestController(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@GetMapping("/candidates")
	public List<Candidate> getCandidates(){
		Sort sortByRegDate = Sort.by("regestrationDate").descending();
		Query query = new Query();
		query.with(sortByRegDate);
		List<Candidate> candidates = mongoTemplate.find(query, Candidate.class);
		
		// List<Candidate> candidates = mongoTemplate.findAll(Candidate.class);
		return candidates;
	}
	
	@GetMapping("/candidates/{candidateId}")
	public Candidate getCandidate(@PathVariable String candidateId){
		Candidate candidate = mongoTemplate.findById(candidateId, Candidate.class);
		return candidate;
	}

	/**
	 * Inserts new candidate with unique full name
	 * 
	 * @param candidate
	 * @return
	 */
	@PostMapping("/candidates")
	public Candidate addCandidate(@RequestBody Candidate candidate) {
		return mongoTemplate.insert(candidate);
	}
}