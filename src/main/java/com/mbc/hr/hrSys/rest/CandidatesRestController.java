package com.mbc.hr.hrSys.rest;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mbc.hr.hrSys.aws.AWSStorageManager;
import com.mbc.hr.hrSys.dao.CandidateDao;
import com.mbc.hr.hrSys.dao.documents.Candidate;
import com.mbc.hr.hrSys.rest.validation.ConstantCheck;

@RestController
@RequestMapping("/api")
@Validated
public class CandidatesRestController {

	@Autowired
	private AWSStorageManager awsStorageManager;

	@Autowired
	private CandidateDao candidateDao;

	private MongoTemplate mongoTemplate;

	public CandidatesRestController(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Get all candidates sorted by regestrationDate
	 * 
	 * @return
	 */
	@GetMapping("/candidates")
	public List<Candidate> getCandidates(
			@RequestHeader(value = "x-admin", required = true) @Valid @ConstantCheck("1") String xAdmin) {
		return candidateDao.getAll(mongoTemplate);
	}

	/**
	 * Gets candidate by id
	 * 
	 * @param candidateId
	 * @return
	 */
	@GetMapping("/candidates/{candidateId}")
	public Candidate getCandidate(
			@RequestHeader(value = "x-admin", required = true) @Valid @ConstantCheck("1") String xAdmin,
			@PathVariable String candidateId) {
		return candidateDao.getById(mongoTemplate, candidateId);
	}

	/**
	 * Inserts new candidate with unique full name
	 * 
	 * @param candidate
	 * @return
	 */
	@PostMapping("/candidates")
	public Candidate addCandidate(@Valid @RequestBody Candidate candidate) {
		return candidateDao.insert(mongoTemplate, candidate);
	}

	/**
	 * Upload candidate CV
	 * 
	 * @param candidate
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/candidateCV")
	public boolean uploadCandidateCV(@RequestParam("candidateId") String candidateId,
			@RequestParam("cvFile") MultipartFile cvFile) throws IOException {

		Candidate candidate = candidateDao.getById(mongoTemplate, candidateId);
		candidate.setCvFileName(candidateId + "_" + cvFile.getOriginalFilename());
		candidateDao.save(mongoTemplate, candidate);

		awsStorageManager.addCandidateCV(candidate.getCvFileName(), cvFile.getInputStream(),
				awsStorageManager.createAWSMetaData(cvFile));
		return true;
	}

	@GetMapping(value = "/candidateCV/{candidateId}")
	public @ResponseBody byte[] downloadCandidateCV(
			@RequestHeader(value = "x-admin", required = true) @Valid @ConstantCheck("1") String xAdmin,
			@PathVariable String candidateId) throws IOException {
		Candidate candidate = candidateDao.getById(mongoTemplate, candidateId);
		return awsStorageManager.downloadCandidateCV(candidate.getCvFileName());
	}
}